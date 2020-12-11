package com.example.artapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.model.AccessToken
import com.example.future_authorization.AuthorizationActivity
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    @Inject
    lateinit var accessTokenDao:AccessTokenDao
    @Inject
    lateinit var scheduler:SchedulerProvider

    private lateinit var tokenDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        injectMe()

        val autIntent = Intent(this, AuthorizationActivity::class.java)
        val homeIntent = Intent(this, MainActivity::class.java)

//        tokenDisposable = accessTokenDao.getAccessToken()
//            .subscribeOn(scheduler.io())
//            .observeOn(scheduler.ui())
//            .subscribe(
//                {data ->
//                    if (data != null)
//                        startNewActivity(homeIntent)
//                    else startNewActivity(autIntent)
//                },
//                {e ->
//                    Log.e(TAG, "ERROR -> ${e.message}")
//                    if (e is EmptyResultSetException)
//                        startNewActivity(autIntent)
//                })
        tokenDisposable = accessTokenDao.insertAccessToken(AccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YzAxMGJiM2VjMDY2ZDQ3Yzc2NWFmZmEiLCJzYWx0X2hhc2giOiJlOGQ3ZGY1ZDM0MzhlYmE3MTA3OTZhZDA5ZmZmZDczNSIsInJvbGVzIjoidXNlciIsInBhcnRuZXJfaWRzIjpbXSwib3RwIjpmYWxzZSwiZXhwIjoyMzkzNTgyNDIxLCJpYXQiOjE2MDQ2NjQwMjEsImF1ZCI6IjVjMDEwYzU4ZWMwNjZkNDdjNzY1YjA3ZSIsImlzcyI6IkdyYXZpdHkiLCJqdGkiOiI1ZmE1M2FkNWQxN2MwMDAwMTJmNmM4YzEifQ.kH__MqgbSKPGrZwL5UuR8vbyfFidGOPKUiB6TUJpO0Q", ""))
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe(
                { startNewActivity(homeIntent)},
                {e ->
                    Log.e(TAG, e.message.toString())
                }
            )
    }

    private fun injectMe(){
        (application as ArtApp).appComponent.inject(this)
    }

    private fun startNewActivity(intent: Intent){
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
       tokenDisposable.dispose()
        super.onDestroy()
    }
}