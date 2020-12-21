package com.example.future_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.navagation.ProfileNav
import com.example.future_profile.databinding.ProfileFragmentBinding
import com.example.future_profile.di.ProfileInjector
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    @Inject
    lateinit var appNavigation:ProfileNav
    private var binding:ProfileFragmentBinding? = null
    private var logOutDisposable:Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()
        binding!!.lifecycleOwner = this
        binding!!.userProfile = viewModel.userProfile
        initLogOutBtn()
    }

    private fun injectMe(){
        (activity?.application as ProfileInjector)
            .getProfileComponentFactory()
            .create()
            .inject(this)
    }

    private fun initLogOutBtn(){
        binding!!.profileSignOutBtn.setOnClickListener {
            logOutDisposable = viewModel.logOut.subscribe(
                    {
                        appNavigation.navFromProfileFragmentToAuthFragment(it)
                        activity?.finish()
                    },
                    {e ->
                        Log.e("LOGOUT_ERROR", e.message ?: "UNKNOWN")
                        Snackbar.make(it, getString(R.string.snackbar_error_msg), Snackbar.LENGTH_SHORT).show()
                    }
            )
        }
    }

    override fun onDestroy() {
        logOutDisposable?.dispose()
        binding = null
        super.onDestroy()
    }

}