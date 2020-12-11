package com.example.search_component.datasource

import androidx.paging.rxjava2.RxPagingSource
import com.example.core.model.SearchResultModel
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.mapper.SearchResultMapper
import io.reactivex.Single

class SearchPagingSource constructor(
        private val remoteSource:SearchRemoteDataSource,
        private val schedulerProvider: SchedulerProvider,
        private val q:String,
        private val type:String?,
        private val mapper:SearchResultMapper
):RxPagingSource<Int, SearchResultModel>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, SearchResultModel>> {
        val position = params.key ?: 0

        return remoteSource.searchQuery(q, params.loadSize, position, type)
                .subscribeOn(schedulerProvider.io())
                .map { it.embedded?.results ?: emptyList() }
                .map { res -> res.map { mapper.map(it) } }
                .map { toLoadResult(it, position, params.loadSize) }
                .observeOn(schedulerProvider.ui())
                .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data:List<SearchResultModel>, position:Int, loadSize:Int):LoadResult<Int, SearchResultModel>{
        return LoadResult.Page(
                data = data,
                prevKey = if (position == 0) null else position - loadSize,
                nextKey = if (data.isEmpty()) null else position + loadSize
        )
    }
}