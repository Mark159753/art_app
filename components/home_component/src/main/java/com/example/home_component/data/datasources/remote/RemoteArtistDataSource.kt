package com.example.home_component.data.datasources.remote

import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.framework_datasourcess.remote.main.ArtsyService
import com.example.home_component.data.datasources.abstraction.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class RemoteArtistDataSource @Inject constructor(
    private val apiService: ArtsyService
):RemoteDataSource<ArtistResponse> {

    override fun getItems(size: Int, offset: Int): Single<ArtistResponse> =
        apiService.getArtists(size, offset, artworks = true)
}