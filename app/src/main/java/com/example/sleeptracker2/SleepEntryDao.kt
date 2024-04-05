package com.example.sleeptracker2

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

    @Query("SELECT AVG(hours) FROM entry_table")
    fun getAvgHours(): Flow<Float>

    @Query("SELECT MIN(hours) FROM entry_table")
    fun getMinHours(): Flow<Int>

    @Query("SELECT MAX(hours) FROM entry_table")
    fun getMaxHours(): Flow<Int>
}