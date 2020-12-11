package com.example.component_authorization.dummy.datasourcess

import com.example.component_authorization.data.datasorcess.abstraction.LocalAuthDataSource
import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.security.InvalidKeyException

class LocalDataSourceTest: LocalAuthDataSource {

    private val storage:MutableList<AccessToken> = ArrayList()

    override fun saveAccessToken(token: AccessToken): Completable {
        return Completable.create {
            storage.add(token)
//            it.onError(InvalidKeyException())
            it.onComplete()
        }
    }

    override fun getAccessToken(): Single<AccessToken> {
        return Single.create {
            if (storage.isNotEmpty())
                it.onSuccess(storage[0])
            else
                it.onError(ArrayIndexOutOfBoundsException())
        }
    }

    override fun cleanAccessToken(): Completable {
        return Completable.create {
            storage.clear()
            it.onComplete()
        }
    }
}