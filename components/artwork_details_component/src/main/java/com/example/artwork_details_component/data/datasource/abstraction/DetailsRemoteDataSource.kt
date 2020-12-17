package com.example.artwork_details_component.data.datasource.abstraction

import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.framework_datasourcess.model.artwork.ArtworkRes
import io.reactivex.Single

interface DetailsRemoteDataSource {

    fun getArtwork(id:String, size:Int, offset:Int):Single<ArtworkRes>

    fun getArtistByArtworkId(artworkId:String):Single<ArtistResponse>

    fun getArtistById(artistId:String):Single<Artist>
}