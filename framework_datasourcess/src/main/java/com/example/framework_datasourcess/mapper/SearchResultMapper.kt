package com.example.framework_datasourcess.mapper

import com.example.core.model.SearchResultModel
import com.example.framework_datasourcess.model.search.Result

class SearchResultMapper:IMapper<Result, SearchResultModel> {

    override fun map(from: Result): SearchResultModel {
        return SearchResultModel(
                from.description,
                from.links?.permalink?.href,
                from.links?.self?.href,
                from.links?.thumbnail?.href,
                from.ogType,
                from.title,
                from.type
        )
    }
}