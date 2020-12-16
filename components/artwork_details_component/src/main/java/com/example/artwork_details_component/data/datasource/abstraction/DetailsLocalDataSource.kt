package com.example.artwork_details_component.data.datasource.abstraction

import androidx.paging.PagingSource
import com.example.framework_datasourcess.model.artist.Artist
import io.reactivex.Completable
import io.reactivex.Single

interface DetailsLocalDataSource<T:Any> {

    fun insertAllItems(artistId:String, list:List<T>)

    fun insertItem(artistId:String, item: T)

    fun insertArtistAndGetArtist(artist: Artist):Single<Artist>

    fun insertArtist(artist: Artist)

    fun getArtistById(id:String): Artist

    fun getAllItemById(id:String): List<T>

    fun getArtistByArtworkId(id: String):Single<Artist>

    fun getItemsPagingSourceById(id:String): PagingSource<Int, T>

    fun deleteAllItems()

    fun deleteById(id:String)
}