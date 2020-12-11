package com.example.home_component.data.repository

import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.framework_datasourcess.mapper.ArtistMapper
import com.example.framework_datasourcess.mapper.ArtworkMapper
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import com.example.home_component.data.datasources.paging.ArtistRxRemoteMediator
import com.example.home_component.data.datasources.paging.ArtworkRxRemoteMediator
import com.example.home_component.domain.repository.HomeArtRepository
import io.reactivex.Flowable
import javax.inject.Inject


@ExperimentalPagingApi
class HomeArtRepositoryImpl @Inject constructor(
    private val artistDataSource: LocalDataSource<Artist>,
    private val artworkDataSource: LocalDataSource<Artwork>,
    private val artistRxRemoteMediator: ArtistRxRemoteMediator,
    private val artworkRxRemoteMediator: ArtworkRxRemoteMediator
):HomeArtRepository {

    override fun getArtworks(): Flowable<PagingData<ArtworkModel>> {
        val mapper = ArtworkMapper()
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false),
            remoteMediator = artworkRxRemoteMediator,
            pagingSourceFactory = { artworkDataSource.getItemsPagingSource()}
            ).flowable.map {
            it.map {art ->
                mapper.map(art)
            }
        }
    }

    override fun getArtists(): Flowable<PagingData<ArtistModel>> {
        val mapper = ArtistMapper()
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false),
            remoteMediator = artistRxRemoteMediator,
            pagingSourceFactory = { artistDataSource.getItemsPagingSource() }
            ).flowable.map {
            it.map{ artist ->
                mapper.map(artist)
            }
        }
    }

    companion object{
        private const val PAGE_SIZE = 20
    }
}