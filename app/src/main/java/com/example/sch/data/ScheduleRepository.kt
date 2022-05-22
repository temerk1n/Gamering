package com.example.sch.data

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import com.example.sch.api.ScheduleApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleApi: ScheduleApi,
    private val scheduleDao: ScheduleDao
) {

    fun getSchedule(dateArr : MutableList<String>): io.reactivex.Observable<List<MatchItemSimplified>> {
        tryToFetchSchedule()
        if (dateArr.isNotEmpty()) {
            Log.d("Rep", dateArr[0])
            Log.d("Rep", dateArr[1])
            return scheduleDao.loadByDateFromDB(dateArr[0], dateArr[1])
        } else {
            return scheduleDao.loadScheduleFromDB()
        }
    }

    private fun clearCache() {
        scheduleDao.clear()
    }

    @SuppressLint("CheckResult")
    private fun tryToFetchSchedule(per_page: String = "50", page: String = "1") {
        // for DB
        val receivedItems = arrayListOf<MatchItemSimplified>()
        // RXJAVA

        var tournament_name :String
        var scheduled_at : String
        var firstOpponentName : String
        var secondOpponentName : String
        var firstOpponentImageURL : String
        var secondOpponentImageURL : String

        scheduleApi.getMatch(page = page, per_page = per_page, token = ScheduleApi.TOKEN).subscribeOn(
            Schedulers.io())
            .doOnNext {
                for (matchItem in it) {
                    // Заполнение полей
                    tournament_name = matchItem.serie.full_name + " " + matchItem.tournament.name
                    scheduled_at = matchItem.scheduled_at
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
                    // Создание нового объекта матча
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
                    // Добавление объекта матча в список receivedItems
                    receivedItems.add(newMatchItem)
                }
            }
            .subscribe({
                clearCache()
                // Добавление объектов матчей в базу данных
                for (matchItem in receivedItems)
                    scheduleDao.insert(matchItem)
            }, {e -> e.printStackTrace()})

    }
}