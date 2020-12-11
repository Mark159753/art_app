package com.example.future_authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.future_authorization.di.AuthFragmentComponent
import com.example.future_authorization.di.AuthInjector
import com.example.future_authorization.state.UiState
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject


class AuthorizationActivity : AppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel:OAuthSignInViewModel by viewModels{ viewModelFactory }
    lateinit var injector:AuthFragmentComponent
    private lateinit var loadingGroup:Group

    lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        injectMe()
        callbackManager = CallbackManager.Factory.create()

        loadingGroup = findViewById(R.id.loading_group)

        subscribeOnUiState()
        initFacebookCallback()
    }

    private fun initFacebookCallback(){
        LoginManager.getInstance().registerCallback(callbackManager, object:FacebookCallback<LoginResult?>{
            override fun onSuccess(result: LoginResult?) {
                result?.accessToken?.let {
                    accessToken -> viewModel.authWithUsersOAuthToken(accessToken.token, "facebook")
                }
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, R.string.cancel_auth_msg, Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException?) {
                viewModel.setUiState(UiState.error(error ?: IllegalStateException(getString(R.string.unknown_error))))
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith(getString(R.string.artsy_scheme_callback))){
            val code = uri.getQueryParameter("code")
            Log.d("ACTIVITY_CODE", code ?: "NULL")
            viewModel.authWithArtsyOAuth2(code!!)
        }
    }

    private fun subscribeOnUiState(){
        viewModel.uiState.observe(this, Observer {
            it.error?.let {e ->  showErrorDialog(e.message ?: getString(R.string.unknown_error)) }
            showLoading(it.isLoading)
            it.token?.let { accessToken ->
                viewModel.navigateToMain(this)
                finish()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        loadingGroup.isVisible = isLoading
    }

    private fun showErrorDialog(msg: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.authorization_error))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok)){ dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun injectMe(){
        injector = (application as AuthInjector)
            .getAuthFragmentComponentFactory()
            .create()
        injector.inject(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}