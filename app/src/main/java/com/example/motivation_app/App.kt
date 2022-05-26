package com.example.motivation_app

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {
    companion object {
        const val API_BASE_URL = "https://api.forismatic.com"
        var quoteService: QuoteService? = null
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofit()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        quoteService = retrofit.create(QuoteService::class.java)
    }
}