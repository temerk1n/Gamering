package com.example.sch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable


@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule_table")
    fun loadScheduleFromDB() : Observable<List<MatchItemSimplified>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(matchItemSimplified: MatchItemSimplified)

    @Query("DELETE FROM schedule_table")
    fun clear()


}