package com.example.sch.api

import android.database.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {

    companion object {
        const val BASE_URL = "https://api.pandascore.co/csgo/matches/"
        const val TOKEN = "3h61HhqgXtzHJRW_txM5kVyeye2pS8dwkzgwXtTvcqZjkoEE-Eo"
    }
    // Как добавить параметры запроса?
    @GET("upcoming")
    fun getMatch(@Query("token") TOKEN: String) : Observable<MatchResponse>
}