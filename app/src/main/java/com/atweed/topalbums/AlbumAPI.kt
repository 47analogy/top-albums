package com.atweed.topalbums

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// get albums data from api
interface AlbumAPI {

    @GET("us/apple-music/coming-soon/all/25/explicit.json")
    fun getAlbums(): Observable<JsonObject>

    companion object {

        fun create(): AlbumAPI {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rss.itunes.apple.com/api/v1/")
                .build()

            return retrofit.create(AlbumAPI::class.java)
        }
    }
}