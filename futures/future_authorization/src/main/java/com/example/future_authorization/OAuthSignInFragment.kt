package com.example.future_authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.core.until.decodeSampledBitmapFromResource
import com.facebook.login.LoginManager
import com.google.android.material.button.MaterialButton


class OAuthSignInFragment : Fragment(R.layout.o_auth_sign_in_fragment) {

    private lateinit var oauthBg:AppCompatImageView
    private lateinit var signInBtn:MaterialButton
    private lateinit var artsyOAuthSignInBtn:MaterialButton
    private lateinit var facebookSignInBtn:MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oauthBg = view.findViewById(R.id.auth_bg)
        signInBtn = view.findViewById(R.id.oauth_sing_in_btn)
        artsyOAuthSignInBtn = view.findViewById(R.id.oauth_artsy_sing_in_btn)
        facebookSignInBtn = view.findViewById(R.id.oauth_facebook_sing_in_btn)

        setImageBG()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        artsyOAuthSignIn()
        googleOAuthSign()
        navigateToSignInFragment()
    }

    private fun googleOAuthSign(){
        facebookSignInBtn.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions((activity as AuthorizationActivity), arrayListOf("public_profile", "email"))
        }
    }

    private fun artsyOAuthSignIn(){
        artsyOAuthSignInBtn.setOnClickListener {
            val host = getString(R.string.oauth_callback_host)
            val scheme = getString(R.string.artsy_scheme_callback)
            val callback = "$scheme://$host"
            redirectArtsyUserToBrowser(BuildConfig.CLIENT_ID, callback)
        }
    }

    private fun redirectArtsyUserToBrowser(client_id:String, redirect_uri:String){
        val uri = Uri.parse("https://api.artsy.net/oauth2/authorize?client_id=$client_id&redirect_uri=$redirect_uri&response_type=code")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun navigateToSignInFragment(){
        signInBtn.setOnClickListener {
            val action = OAuthSignInFragmentDirections.actionOAuthSignInFragmentToLoginPasswordSignInFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun setImageBG(){
        val height = calculateHeight()
        oauthBg.setImageBitmap(
                decodeSampledBitmapFromResource(
                        resources, R.drawable.log_in_bg, height, height
                )
        )
    }

    private fun calculateHeight():Int{
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}