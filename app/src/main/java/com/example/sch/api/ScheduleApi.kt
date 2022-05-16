package com.example.sch.api

import com.example.sch.data.MatchItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {

    companion object {
        const val BASE_URL = "https://api.pandascore.co/csgo/matches/"
        const val TOKEN = "3h61HhqgXtzHJRW_txM5kVyeye2pS8dwkzgwXtTvcqZjkoEE-Eo"
    }
    // Как добавить параметры запроса?
    @GET("upcoming?")
    fun getMatch(@Query("page") page : String, @Query("per_page") per_page : String, @Query("token") token: String) : io.reactivex.Observable<List<MatchItem>>
}