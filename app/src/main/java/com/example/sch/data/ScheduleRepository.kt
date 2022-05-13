package com.example.sch.data

import android.annotation.SuppressLint
import android.util.Log
import com.example.sch.api.ScheduleApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleApi: ScheduleApi,
    private val scheduleDao: ScheduleDao
) {


    fun getSchedule(): io.reactivex.Observable<List<MatchItemSimplified>> {
        tryToFetchSchedule()
        return scheduleDao.loadScheduleFromDB()
    }

    private fun clearCache() {
        scheduleDao.clear()
    }

    @SuppressLint("CheckResult")
    private fun tryToFetchSchedule() {
        // for DB
        val receivedItems = arrayListOf<MatchItemSimplified>()
        //Log.d("My", "getMatch call")
        // RXJAVA
        val per_page : String = "10"
        val page : String = "1"
        scheduleApi.getMatch(page = page, per_page = per_page, token = ScheduleApi.TOKEN).subscribeOn(
            Schedulers.io())
            .doOnNext {
                for (matchItem in it) {
                    val newMatchItem = MatchItemSimplified(
                        tournament_name = matchItem.serie.full_name + " " + matchItem.tournament.name,
                        firstOpponentName = matchItem.opponents[0].opponent.name,
                        secondOpponentName = matchItem.opponents[1].opponent.name,
                        firstOpponentImageURL = matchItem.opponents[0].opponent.image_url,
                        secondOpponentImageURL = matchItem.opponents[1].opponent.image_url,
                    )
                    receivedItems.add(newMatchItem)
                }
            }
            .subscribe({
                clearCache()
                for (matchItem in receivedItems)
                    scheduleDao.insert(matchItem)
            }, {e -> e.printStackTrace()})

    }
}