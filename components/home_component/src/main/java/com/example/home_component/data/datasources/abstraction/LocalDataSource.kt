package com.example.home_component.data.datasources.abstraction

import androidx.paging.PagingSource

interface LocalDataSource<T:Any> {

    fun insertAllItems(list:List<T>)

    fun insertItem(item: T)

    fun getAllItem(): List<T>

    fun getItemsPagingSource(): PagingSource<Int, T>

    fun deleteAllItems()
}