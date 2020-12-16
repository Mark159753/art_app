package com.example.future_artwork_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.artwork_details_component.domain.interactor.ArtworkByArtistUseCase
import com.example.artwork_details_component.domain.interactor.ArtistByArtworkIdUseCase
import com.example.core.exception.UnknownArtistException
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.future_artwork_details.state.UiState
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArtworkDetailsViewModel @Inject constructor(
        private val artworkByArtistUseCase: ArtworkByArtistUseCase,
        private val artistByArtworkIdUseCase: ArtistByArtworkIdUseCase
): ViewModel() {

    private val _artistLiveData = MutableLiveData<ArtistModel>()
    val artistLiveData:LiveData<ArtistModel>
    get() = _artistLiveData

    private val _artworkLiveData = MutableLiveData<ArtworkModel>()
    val artworkLiveData:LiveData<ArtworkModel>
        get() = _artworkLiveData

    private val _uiState = MutableLiveData<UiState>()
    val uiState:LiveData<UiState>
    get() = _uiState

    private var currentArtworkPagingRes: Flowable<PagingData<ArtworkModel>>? = null

    fun getArtworkPagingByArtwork(artworkId:String):Flowable<PagingData<ArtworkModel>>{
        return if (_artistLiveData.value == null){
                    artistByArtworkIdUseCase.getArtworkByArtworkId(artworkId)
                            .toFlowable()
                            .flatMap { getArtworkPagingByArtist(it) }
        }else{
            getArtworkPagingByArtist(artistLiveData.value!!)
        }
    }

    fun getArtworkPagingByArtist(artist:ArtistModel):Flowable<PagingData<ArtworkModel>>{
        if (currentArtworkPagingRes == null) {
            _artistLiveData.postValue(artist)
            currentArtworkPagingRes = artworkByArtistUseCase.getArtworkByArtist(artist.id)
                    .cachedIn(viewModelScope)
        }
        return currentArtworkPagingRes!!
    }

    fun setUiState(state: UiState){
        _uiState.postValue(state)
    }

    fun setArtworkLiveData(artwork:ArtworkModel){
        _artworkLiveData.postValue(artwork)
    }

}