package com.example.search_component.repository

import androidx.paging.PagingData
import com.example.core.model.SearchResultModel
import io.reactivex.Flowable

interface SearchRepository {

    fun querySearch(q:String, type:String?):Flowable<PagingData<SearchResultModel>>
}