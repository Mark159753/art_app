package com.example.home_component.domain.interactor

import androidx.paging.PagingData
import com.example.core.model.ArtistModel
import com.example.home_component.domain.repository.HomeArtRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ArtistUseCase @Inject constructor(
    private val repository: HomeArtRepository
):UseCase<Flowable<PagingData<ArtistModel>>> {

    override fun execute(): Flowable<PagingData<ArtistModel>> {
        return repository.getArtists()
    }
}