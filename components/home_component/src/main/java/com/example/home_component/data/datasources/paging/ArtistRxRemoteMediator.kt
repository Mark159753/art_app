package com.example.home_component.data.datasources.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import com.example.home_component.data.datasources.abstraction.RemoteDataSource
import com.example.home_component.data.datasources.local.LocalArtistDataSource
import com.example.home_component.data.datasources.remote.RemoteArtistDataSource
import javax.inject.Inject

@ExperimentalPagingApi
class ArtistRxRemoteMediator @Inject constructor(
    remote: RemoteDataSource<ArtistResponse>,
    schedulerProvider: SchedulerProvider,
    private val dataBase: ArtsyDataBase,
    private val local: LocalDataSource<Artist>
):BaseRxRemoteMediator<Artist, ArtistResponse>(remote, schedulerProvider) {

    private val keyType:Int = ArtsyRemoteKeys.ARTIST_TYPE

    override fun insertToDb(
        page: Int,
        loadType: LoadType,
        state: PagingState<Int, Artist>,
        data: ArtistResponse
    ): MediatorResult {
        val items = data.embedded.artists
        val endOfPage = items.isEmpty()
        dataBase.runInTransaction {
            if (loadType == LoadType.REFRESH){
                dataBase.getArtsyRemoteKeysDao().deletesAllByType(keyType)
                local.deleteAllItems()
            }
            val prev = if (page == START_PAGE) null else page.minus(state.config.pageSize)
            val next = if (endOfPage) null else page + state.config.pageSize
            val keys = items.map {
                ArtsyRemoteKeys(keyType, it.id, prev, next)
            }
            dataBase.getArtsyRemoteKeysDao().insert(keys)
            local.insertAllItems(items)
        }
        return MediatorResult.Success(endOfPage)
    }

    override fun getRemoteKeyForLastItem(state: PagingState<Int, Artist>): ArtsyRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let{ item ->
            dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(item.id, keyType)
        }
    }

    override fun getRemoteKeyForFirstItem(state: PagingState<Int, Artist>): ArtsyRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty()}?.data?.firstOrNull()?.let { item ->
            dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(item.id, keyType)
        }
    }

    override fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Artist>): ArtsyRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dataBase.getArtsyRemoteKeysDao().getRemotesKeysByItemId(id, keyType)
            }
        }
    }
}