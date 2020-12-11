package com.example.search_component.datasource

import com.example.framework_datasourcess.model.search.SearchResponse
import io.reactivex.Single

interface SearchRemoteDataSource {

    fun searchQuery(q:String, size:Int, offset:Int, type:String?):Single<SearchResponse>
}