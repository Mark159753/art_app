package com.example.search_component.datasource

import com.example.framework_datasourcess.model.search.SearchResponse
import com.example.framework_datasourcess.remote.main.ArtsyService
import io.reactivex.Single
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val apiService: ArtsyService
): SearchRemoteDataSource {

    override fun searchQuery(q: String, size:Int, offset:Int, type:String?): Single<SearchResponse> {
        return apiService.searchQuery(q, size, offset, type)
    }
}