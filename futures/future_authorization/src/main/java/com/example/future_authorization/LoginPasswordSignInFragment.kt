package com.example.future_authorization

import android.os.Bundle
import android.view.View
import androidx.core.util.PatternsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.future_authorization.state.UiState
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class LoginPasswordSignInFragment : Fragment(R.layout.fragment_login_password_sign_in) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: OAuthSignInViewModel by activityViewModels{ viewModelFactory }


    private lateinit var toolbar:MaterialToolbar
    private lateinit var emailField:TextInputEditText
    private lateinit var emailContainer:TextInputLayout
    private lateinit var passwordField:TextInputEditText
    private lateinit var passwordContainer:TextInputLayout
    private lateinit var signInBtn:MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.sign_in_toolbar)
        emailField = view.findViewById(R.id.sign_in_email_input)
        emailContainer = view.findViewById(R.id.sign_in_email_container)
        passwordField = view.findViewById(R.id.sign_in_password_input)
        passwordContainer = view.findViewById(R.id.sign_in_password_container)
        signInBtn = view.findViewById(R.id.sing_in_btn)

        toolbar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()
        signInBtn()
        listenTextInputsChanges()
    }

    private fun signInBtn(){
        signInBtn.setOnClickListener {
            viewModel.setUiState(UiState.loading())
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val isEmailCorrect = isEmailValidate(email)
            val isPasswordCorrect = isPasswordValidate(password)
            if (isEmailCorrect && isPasswordCorrect){
                viewModel.authWithLoggingPassword(email, password)
            }
        }
    }

    private fun listenTextInputsChanges(){
        emailField.doAfterTextChanged {
            emailContainer.error = null
        }
        passwordField.doAfterTextChanged {
            passwordContainer.error = null
        }
    }

    private fun isPasswordValidate(password:String?):Boolean{
        return if (password == null || password.isEmpty()) {
            passwordContainer.error = getString(R.string.empty_password_error_msg)
            false
        }else if (password.length < 6){
            passwordContainer.error = getString(R.string.password_lenght_error_msg)
            false
        }
        else true
    }

    private fun isEmailValidate(email:String?):Boolean{
        return if (email == null || email.isEmpty()){
            emailContainer.error = getString(R.string.empty_field_error_msg)
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            emailContainer.error = getString(R.string.bad_email_address_error_msg)
            false
        }else true
    }


    private fun injectMe(){
        (activity as AuthorizationActivity)
            .injector
            .inject(this)
    }

}