package com.example.home_component.domain.repository

import androidx.paging.PagingData
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import io.reactivex.Flowable

interface HomeArtRepository {

     fun getArtworks(): Flowable<PagingData<ArtworkModel>>
     fun getArtists():Flowable<PagingData<ArtistModel>>
}