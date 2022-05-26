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


    fun getSchedule(dateArr : MutableList<String>): io.reactivex.Observable<List<MatchItemSimplified>> {
        tryToFetchSchedule()
        Log.d("observe", '1'.toString())
        if (dateArr.isNotEmpty()) {
            Log.d("Rep", dateArr[0])
            Log.d("Rep", dateArr[1])
            var month : String
            var dayOfMonth : String
            if (dateArr[0].length == 1) {
                month = "0${dateArr[0]}"
            } else {
                month = dateArr[0]
            }
            if (dateArr[1].length == 1) {
                dayOfMonth = "0${dateArr[1]}"
            }
            else {
                dayOfMonth = dateArr[1]
            }
            return scheduleDao.loadByDateFromDB(month, dayOfMonth)
        } else {
            return scheduleDao.loadScheduleFromDB()
        }
    }

    private fun clearCache() {
        scheduleDao.clear()
    }

    @SuppressLint("CheckResult")
    private fun tryToFetchSchedule() {
        // for DB
        val receivedItems = arrayListOf<MatchItemSimplified>()

        // RXJAVA
        val per_page = "50"
        val page = "1"

        var tournament_name : String
        var scheduled_at : String
        var firstOpponentName : String
        var secondOpponentName : String
        var firstOpponentImageURL : String
        var secondOpponentImageURL : String

        scheduleApi.getMatch(page = page, per_page = per_page, token = ScheduleApi.TOKEN).subscribeOn(
            Schedulers.io())
            .doOnNext {
                for (matchItem in it) {
                    tournament_name = matchItem.serie.full_name + " " + matchItem.tournament.name
                    scheduled_at = matchItem.scheduled_at
                    if (matchItem.opponents.isNotEmpty()) {
                        firstOpponentName = matchItem.opponents[0].opponent.name
                        secondOpponentName = if (matchItem.opponents.size > 1) matchItem.opponents[1].opponent.name else "TBD"
                        firstOpponentImageURL = matchItem.opponents[0].opponent.image_url
                        secondOpponentImageURL = if (matchItem.opponents.size > 1) matchItem.opponents[1].opponent.image_url else "null"
                    } else {
                        firstOpponentName = "TBD"
                        secondOpponentName = "TBD"
                        firstOpponentImageURL = "null"
                        secondOpponentImageURL = "null"
                    }
                    val array : MutableList<String> = formatDate(scheduled_at)
                    val newMatchItem = MatchItemSimplified(
                        tournament_name = tournament_name,
                        month = array[0],
                        monthDay = array[1],
                        hour = array[2],
                        minute = array[3],
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