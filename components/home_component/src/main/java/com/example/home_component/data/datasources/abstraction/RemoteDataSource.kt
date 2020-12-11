package com.example.home_component.data.datasources.abstraction

import io.reactivex.Single

interface RemoteDataSource<T> {

    fun getItems(size:Int, offset:Int): Single<T>
}