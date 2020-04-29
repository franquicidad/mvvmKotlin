package com.felipefranco.mvvm.network

import com.felipefranco.mvvm.network.model.ArtistsResponse
import com.felipefranco.mvvm.network.model.ArtistDetailsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val COUNTRY = "colombia"
private const val API_KEY = "829751643419a7128b7ada50de590067"
private const val BASE_URL = "http://ws.audioscrobbler.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("/2.0/?method=geo.gettopartists&country=$COUNTRY&api_key=$API_KEY&format=json")
    fun getArtistsAsync(): Deferred<ArtistsResponse>

    @GET("/2.0/?method=artist.getinfo&api_key=$API_KEY&format=json")
    fun getArtistDetailAsync(@Query("mbid") mbid: String): Deferred<ArtistDetailsResponse>
}

object LastfmApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}