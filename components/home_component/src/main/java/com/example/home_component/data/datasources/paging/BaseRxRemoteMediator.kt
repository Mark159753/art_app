package com.example.home_component.data.datasources.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.home_component.data.datasources.abstraction.RemoteDataSource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRxRemoteMediator<TO:Any, NETWORK>(
    private val remote:RemoteDataSource<NETWORK>,
    private val schedulers:SchedulerProvider
):RxRemoteMediator<Int, TO>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, TO>
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
                    remote.getItems(
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

    abstract fun insertToDb(page: Int, loadType: LoadType, state: PagingState<Int, TO>, data: NETWORK): MediatorResult

    abstract fun getRemoteKeyForLastItem(state: PagingState<Int, TO>): ArtsyRemoteKeys?

    abstract fun getRemoteKeyForFirstItem(state: PagingState<Int, TO>): ArtsyRemoteKeys?

    abstract fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TO>): ArtsyRemoteKeys?


    companion object{
        const val INVALID_PAGE = -1
        const val START_PAGE = 0
    }
}