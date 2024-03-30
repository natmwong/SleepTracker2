package com.example.sleeptracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepEntryDao {
    @Query("SELECT * FROM entry_table")
    fun getAll(): Flow<List<SleepEntryEntity>>

    @Insert
    fun insert(entry: SleepEntryEntity)

    @Query("DELETE FROM entry_table")
    fun deleteAll()
}