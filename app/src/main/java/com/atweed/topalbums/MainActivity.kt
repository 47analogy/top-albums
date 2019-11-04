package com.atweed.topalbums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    val client by lazy {
        AlbumAPI.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayAlbums()
    }

    // log albums (verifying)
    fun displayAlbums() {
        disposable = client.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("ALBUMS", "" + result.feed.results) },
                { error -> Log.e("ERROR", error.message) }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}