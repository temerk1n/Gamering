package com.example.sch.data

import androidx.room.*
import io.reactivex.Observable


@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule_table")
    fun loadScheduleFromDB() : Observable<List<MatchItemSimplified>>

    @Query("SELECT * FROM schedule_table WHERE month >= :month AND monthDay >= :monthDay")
    fun loadByDateFromDB(
        month : String?,
        monthDay : String?
    ) : Observable<List<MatchItemSimplified>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(matchItemSimplified: MatchItemSimplified)

    @Query("DELETE FROM schedule_table")
    fun clear()


}