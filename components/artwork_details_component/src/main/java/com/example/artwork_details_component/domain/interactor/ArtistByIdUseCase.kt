package com.example.artwork_details_component.domain.interactor

import com.example.artwork_details_component.domain.repository.ArtworkDetailsRepository
import com.example.core.model.ArtistModel
import io.reactivex.Single
import javax.inject.Inject

class ArtistByIdUseCase @Inject constructor(
        private val repository: ArtworkDetailsRepository
) {

    fun getArtistById(id:String): Single<ArtistModel> {
        return repository.getArtistById(id)
    }
}