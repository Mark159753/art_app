package com.example.search_component.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.core.model.SearchResultModel
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.mapper.SearchResultMapper
import com.example.search_component.datasource.SearchPagingSource
import com.example.search_component.datasource.SearchRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteSource:SearchRemoteDataSource,
    private val schedulerProvider: SchedulerProvider
):SearchRepository {

    override fun querySearch(q: String, type: String?): Flowable<PagingData<SearchResultModel>> {
        val pagingSource = SearchPagingSource(
            remoteSource,
            schedulerProvider,
            q,
            type,
            SearchResultMapper()
        )
        return Pager(
            config = PagingConfig(
                pageSize =  10,
                initialLoadSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }
}