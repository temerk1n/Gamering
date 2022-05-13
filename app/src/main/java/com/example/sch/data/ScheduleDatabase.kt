package com.example.sch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [MatchItemSimplified::class], version = 9)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao() : ScheduleDao

    class Callback @Inject constructor(
        private val database: Provider<ScheduleDatabase>
    ) : RoomDatabase.Callback()
}