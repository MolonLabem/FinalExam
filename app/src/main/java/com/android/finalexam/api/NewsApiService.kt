package com.android.finalexam.api

import com.android.finalexam.model.News
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://saurav.tech/NewsAPI/top-headlines"

interface NewsApiService {
    companion object Factory {
        fun create(): NewsApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(NewsApiService::class.java);
        }
    }
    @GET("/category/{categoryName}/{countryName}")
    fun getNews(@Path("categoryName") categoryName: String,
                @Path("countryName") countryName: String):
     Observable<List<News>>

}