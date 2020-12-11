package com.example.home_component.data.datasources.remote

import com.example.framework_datasourcess.model.artwork.ArtworkRes
import com.example.framework_datasourcess.remote.main.ArtsyService
import com.example.home_component.data.datasources.abstraction.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class RemoteArtworksDataSource @Inject constructor(
    private val artsyService: ArtsyService
): RemoteDataSource<ArtworkRes> {

    override fun getItems(size: Int, offset: Int): Single<ArtworkRes>
            = artsyService.getArtworks(size, offset)
}