package com.example.future_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.home_component.domain.interactor.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    artworkUseCase: UseCase<Flowable<PagingData<ArtworkModel>>>,
    artistUseCase: UseCase<Flowable<PagingData<ArtistModel>>>
) : ViewModel() {


    val artworks = artworkUseCase.execute()
        .cachedIn(viewModelScope)

    val artists = artistUseCase.execute()
        .cachedIn(viewModelScope)
}