package com.example.future_profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.UserProfileModel
import com.example.framework_datasourcess.model.user.User
import com.example.profile_component.repository.UserProfileRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfileModel>()
    val userProfile:LiveData<UserProfileModel>
        get() = _userProfile

    private lateinit var userDisposable: Disposable

    init {
        getUserProfile()
    }

    val logOut = repository.logOut()

    private fun getUserProfile(){
        userDisposable = repository.getUserDetails()
                .subscribe(
                        {data ->
                            _userProfile.postValue(data)
                        },
                        {e ->
                            Log.e("PROFILE_ERROR", e.message ?: "Unknown")
                        }
                )
    }



    override fun onCleared() {
        userDisposable.dispose()
        super.onCleared()
    }
        
}