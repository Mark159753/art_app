package com.example.future_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.core.model.SearchResultModel
import com.example.future_search.searchFilter.SearchFilter
import com.example.search_component.repository.SearchRepository
import io.reactivex.Flowable
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    @Volatile
    var searchCategoryType:SearchFilter.SearchCategory = SearchFilter.SearchCategory.ALL

    private var oldSearchCategory:SearchFilter.SearchCategory? = null

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flowable<PagingData<SearchResultModel>>? = null

    fun search(query:String, type:String?):Flowable<PagingData<SearchResultModel>>{
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null && oldSearchCategory != null && oldSearchCategory == searchCategoryType) {
            return lastResult
        }
        oldSearchCategory = searchCategoryType
        currentQueryValue = query
        val newResult = repository.querySearch(query, type)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}