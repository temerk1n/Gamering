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
        val per_page = "20"
        val page = "1"

        var tournament_name :String
        var original_scheduled_at : String
        var firstOpponentName : String
        var secondOpponentName : String
        var firstOpponentImageURL : String
        var secondOpponentImageURL : String

        scheduleApi.getMatch(page = page, per_page = per_page, token = ScheduleApi.TOKEN).subscribeOn(
            Schedulers.io())
            .doOnNext {
                for (matchItem in it) {
                    tournament_name = matchItem.serie.full_name + " " + matchItem.tournament.name
                    original_scheduled_at = matchItem.original_scheduled_at
                    if (matchItem.opponents.isNotEmpty()) {
                        firstOpponentName = matchItem.opponents[0].opponent.name
                        secondOpponentName = matchItem.opponents[1].opponent.name
                        firstOpponentImageURL = matchItem.opponents[0].opponent.image_url
                        secondOpponentImageURL = matchItem.opponents[1].opponent.image_url
                    } else {
                        firstOpponentName = "TBD"
                        secondOpponentName = "TBD"
                        firstOpponentImageURL = "null"
                        secondOpponentImageURL = "null"
                    }
                    val newMatchItem = MatchItemSimplified(
                        tournament_name = tournament_name,
                        original_scheduled_at = original_scheduled_at,
                        firstOpponentName = firstOpponentName,
                        secondOpponentName = secondOpponentName,
                        firstOpponentImageURL = firstOpponentImageURL,
                        secondOpponentImageURL = secondOpponentImageURL,
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