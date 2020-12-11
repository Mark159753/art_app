package com.example.home_component.domain.interactor

import androidx.paging.PagingData
import com.example.core.model.ArtworkModel
import com.example.home_component.domain.repository.HomeArtRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ArtworkUseCase @Inject constructor(
    private val repository: HomeArtRepository
):UseCase<Flowable<PagingData<ArtworkModel>>> {

    override fun execute(): Flowable<PagingData<ArtworkModel>> {
        return repository.getArtworks()
    }
}