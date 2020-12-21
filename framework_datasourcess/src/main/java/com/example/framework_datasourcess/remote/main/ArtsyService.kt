package com.example.framework_datasourcess.remote.main

import com.example.framework_datasourcess.model.DeleteAccessToken
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.framework_datasourcess.model.artwork.ArtworkRes
import com.example.framework_datasourcess.model.search.SearchResponse
import com.example.framework_datasourcess.model.user.CurrentUser
import com.example.framework_datasourcess.model.user.UserDetails
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtsyService {

    @DELETE("api/tokens/access_token")
    fun logout(): Single<DeleteAccessToken>

    @GET("api/artworks")
    fun getArtworks(
        @Query("size") size:Int,
        @Query("offset") offset:Int,
        @Query("artist_id") artist_id:String? = null,
        @Query("partner_id") partner_id:String? = null,
        @Query("show_id") show_id:String? = null,
        @Query("collection_id") collection_id:String? = null,
        @Query("user_id") user_id:String? = null
    ):Single<ArtworkRes>

    @GET("api/artists")
    fun getArtists(
        @Query("size") size:Int? = null,
        @Query("offset") offset:Int? = null,
        @Query("artwork_id") artwork_id:String? = null,
        @Query("similar_to_artist_id") similar_to_artist_id:String? = null,
        @Query("similarity_type") similarity_type:String? = null,
        @Query("gene_id") gene_id:String? = null,
        @Query("artworks") artworks:Boolean? = null,
        @Query("published_artworks") published_artworks:Boolean? = null,
        @Query("partner_id") partner_id:String? = null
    ):Single<ArtistResponse>

    @GET("api/artists/{id}")
    fun getArtistById(
            @Path("id") id:String
    ):Single<Artist>

    @GET("api/search")
    fun searchQuery(
        @Query("q") q:String,
        @Query("size") size:Int,
        @Query("offset") offset:Int,
        @Query("type") type:String? = null
    ):Single<SearchResponse>

    @GET("api/current_user")
    fun getCurrentUser():Single<CurrentUser>

    @GET("api/user_details/{id}")
    fun getUserDetails(
            @Path("id") id:String
    ):Single<UserDetails>
}