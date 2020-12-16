package com.example.artwork_details_component.domain.interactor

import androidx.paging.PagingData
import com.example.artwork_details_component.domain.repository.ArtworkDetailsRepository
import com.example.core.model.ArtworkModel
import io.reactivex.Flowable
import javax.inject.Inject


class ArtworkByArtistUseCase @Inject constructor(
        private val repository: ArtworkDetailsRepository
) {
    fun getArtworkByArtist(artistId:String): Flowable<PagingData<ArtworkModel>>{
        return repository.getArtworksPagingByArtistId(artistId)
    }
}