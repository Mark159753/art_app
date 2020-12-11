package com.example.future_authorization

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.component_authorization.domain.repository.AuthRepository
import com.example.core.navagation.AuthorizationToMainActivityNav
import com.example.core.scheduler.SchedulerProvider
import com.example.future_authorization.state.UiState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class OAuthSignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val schedulerProvider: SchedulerProvider,
    private val navigation:AuthorizationToMainActivityNav
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _uiState = MutableLiveData<UiState>()
    val uiState:LiveData<UiState>
        get() = _uiState

    fun setUiState(state: UiState){
        _uiState.postValue(state)
    }

    fun navigateToMain(context: Context){
        navigation.navigateToMainActivity(context)
    }

    fun authWithArtsyOAuth2(code:String){
        disposable.add(repository.authWithArtsyOAuth2(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code)
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { _uiState.postValue(UiState.loading()) }
            .subscribe(
                {data ->
                    _uiState.postValue(UiState.success(data))
                },
                {e ->
                    _uiState.postValue(UiState.error(e))
                }
            )
        )
    }

    fun authWithLoggingPassword(email:String,
                                password:String){
        disposable.add(repository.authWithLoggingPassword(
            BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET,
            email, password
        )
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { _uiState.postValue(UiState.loading()) }
            .subscribe(
                {data ->
                    _uiState.postValue(UiState.success(data))
                },
                {e ->
                    _uiState.postValue(UiState.error(e))
                }
            )
        )
    }

    fun authWithUsersOAuthToken(
        oauth_token:String,
        oauth_provider:String
    ){
        disposable.add(repository.authWithUsersOAuthToken(
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET,
            oauth_token,
            oauth_provider
        ).observeOn(schedulerProvider.ui())
            .doOnSubscribe { _uiState.postValue(UiState.loading()) }
            .subscribe(
                {data ->
                    _uiState.postValue(UiState.success(data))
                },
                {e ->
                    _uiState.postValue(UiState.error(e))
                }
            )
        )
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}