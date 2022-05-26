package com.example.motivation_app

import retrofit2.Call
import retrofit2.http.GET


interface QuoteService {
    @GET("/api/1.0/?method=getQuote&format=json")
    fun getNextQuote(): Call<Quote?>?
}