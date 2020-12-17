package com.example.artwork_details_component.data.datasource.remote

import com.example.artwork_details_component.data.datasource.abstraction.DetailsRemoteDataSource
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.framework_datasourcess.model.artwork.ArtworkRes
import com.example.framework_datasourcess.remote.main.ArtsyService
import io.reactivex.Single
import javax.inject.Inject

class DetailsRemoteArtistDataSource @Inject constructor(
    private val api:ArtsyService
):DetailsRemoteDataSource {

    override fun getArtwork(id: String, size: Int, offset: Int): Single<ArtworkRes> {
        return api.getArtworks(artist_id = id, size = size, offset = offset)
    }

    override fun getArtistByArtworkId(artworkId: String):Single<ArtistResponse> {
        return api.getArtists(artwork_id = artworkId)
    }

    override fun getArtistById(artistId: String): Single<Artist> {
        return api.getArtistById(artistId)
    }
}