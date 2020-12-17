package com.example.artwork_details_component.data.repository

import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.example.artwork_details_component.data.datasource.abstraction.DetailsLocalDataSource
import com.example.artwork_details_component.data.datasource.abstraction.DetailsRemoteDataSource
import com.example.artwork_details_component.data.datasource.paging.DetailsArtworkRxMediator
import com.example.artwork_details_component.domain.repository.ArtworkDetailsRepository
import com.example.core.exception.NoConnectivityException
import com.example.core.exception.UnknownArtistException
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.mapper.ArtistMapper
import com.example.framework_datasourcess.mapper.ArtworkMapper
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.artwork.Artwork
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class ArtworkDetailsRepositoryImpl @Inject constructor(
        private val schedulers: SchedulerProvider,
        private val dataBase: ArtsyDataBase,
        private val remote: DetailsRemoteDataSource,
        private val local: DetailsLocalDataSource<Artwork>
):ArtworkDetailsRepository{

    @ExperimentalPagingApi
    override fun getArtworksPagingByArtistId(artistId: String): Flowable<PagingData<ArtworkModel>> {
        val mediator = DetailsArtworkRxMediator(schedulers, dataBase, remote, local, ArtsyRemoteKeys.ARTWORK_DETAILS_TYPE, artistId)
        val mapper = ArtworkMapper()
        return Pager(
                config = PagingConfig(
                        pageSize = PAGE_SIZE,
                        enablePlaceholders = false
                ),
                remoteMediator = mediator,
                pagingSourceFactory = { local.getItemsPagingSourceById(artistId) }
        ).flowable.map {
            it.map { artist ->
                mapper.map(artist)
            }
        }
    }


    override fun getArtistByArtworkId(id:String):Single<ArtistModel>{
        val mapper = ArtistMapper()
        return remote.getArtistByArtworkId(id)
                .subscribeOn(schedulers.io())
                .map { it.embedded.artists[0] }
                .flatMap { local.insertArtistAndGetArtist(it) }
                .map { mapper.map(it) }
                .onErrorResumeNext(object :Function<Throwable, Single<ArtistModel>>{
                    override fun apply(t: Throwable): Single<ArtistModel> {
                        when (t) {
                            is NoConnectivityException -> {
                                return local.getArtistByArtworkId(id)
                                    .map { mapper.map(it) }
                            }
                            is IndexOutOfBoundsException -> throw UnknownArtistException("Empty Artist")
                            else -> throw t
                        }
                    }
        })
    }

    override fun getArtistById(id: String): Single<ArtistModel> {
        val mapper = ArtistMapper()
        return remote.getArtistById(id)
                .subscribeOn(schedulers.io())
                .flatMap { local.insertArtistAndGetArtist(it) }
                .map { mapper.map(it) }
    }

    companion object{
        private const val PAGE_SIZE = 20
    }
}