package com.example.artwork_details_component.domain.repository

import androidx.paging.PagingData
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import io.reactivex.Flowable
import io.reactivex.Single

interface ArtworkDetailsRepository {

    fun getArtworksPagingByArtistId(artistId:String): Flowable<PagingData<ArtworkModel>>

    fun getArtistByArtworkId(id:String): Single<ArtistModel>

    fun getArtistById(id:String):Single<ArtistModel>
}