package com.example.artwork_details_component.data.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.example.artwork_details_component.data.datasource.abstraction.DetailsLocalDataSource
import com.example.artwork_details_component.data.datasource.abstraction.DetailsRemoteDataSource
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.artwork.ArtworkRes
import io.reactivex.Single
import java.io.InvalidObjectException


@ExperimentalPagingApi
class DetailsArtworkRxMediator constructor(
    private val schedulers: SchedulerProvider,
    private val dataBase: ArtsyDataBase,
    private val remote:DetailsRemoteDataSource,
    private val local:DetailsLocalDataSource<Artwork>,
    private val keyType:Int,
    private val artistId:String
):RxRemoteMediator<Int, Artwork>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, Artwork>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(schedulers.io())
            .map {
                when(it){
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(state.config.pageSize) ?: START_PAGE
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                            ?: throw InvalidObjectException("Result is empty")

                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { page ->
                if (page == INVALID_PAGE){
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                }else{
                    remote.getArtwork(
                        artistId,
                        state.config.pageSize,
                        page
                    )
                        .map {
                            insertToDb(page, loadType, state, it)
                        }
                        .onErrorReturn { MediatorResult.Error(it) }
                }
            }
    }

    private fun insertToDb(
        page: Int,
        loadType: LoadType,
        state: PagingState<Int, Artwork>,
        data: ArtworkRes
    ):MediatorResult {
        val items = data.embedded.artworks
        val endOfPage = items.isEmpty()
        dataBase.runInTransaction {
            if (loadType == LoadType.REFRESH) {
                dataBase.getArtsyRemoteKeysDao().deletesAllByType(keyType)
                local.deleteById(artistId)
            }
            val prev = if (page == START_PAGE) null else page.minus(state.config.pageSize)
            val next = if (endOfPage) null else page + state.config.pageSize
            val keys = items.map {
                ArtsyRemoteKeys(keyType, it.id, prev, next)
            }
            dataBase.getArtsyRemoteKeysDao().insert(keys)
            local.insertAllItems(artistId, items)
        }
        return MediatorResult.Success(endOfPage)
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, Artwork>): ArtsyRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let{ item ->
            dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(item.id, keyType)
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Artwork>): ArtsyRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty()}?.data?.firstOrNull()?.let { item ->
            dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(item.id, keyType)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Artwork>): ArtsyRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(id, keyType)
            }
        }
    }

    companion object{
        const val INVALID_PAGE = -1
        const val START_PAGE = 0
    }
}