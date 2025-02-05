package com.example.dialme.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CallHistoryDao {

    // Insert a new call history record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCall(call: call_history_entity)

    // Update an existing call history record
    @Update
    suspend fun updateCall(call: call_history_entity)

    // Delete a specific call history record
    @Delete
    suspend fun deleteCall(call: call_history_entity)

    // Get all call history records sorted by latest call time (Descending)
    @Query("SELECT * FROM call_history_entity ORDER BY last_call DESC")
    fun getAllCallHistory(): Flow<List<call_history_entity>>

    // Get call history by contact number
    @Query("SELECT * FROM call_history_entity WHERE contact_number = :number ORDER BY last_call DESC")
    fun getCallHistoryByNumber(number: String): Flow<List<call_history_entity>>

    // Get missed calls only
    @Query("SELECT * FROM call_history_entity WHERE last_status = 'missed' ORDER BY last_call DESC")
    fun getMissedCalls(): Flow<List<call_history_entity>>

    // Get received calls only
    @Query("SELECT * FROM call_history_entity WHERE last_status = 'received' ORDER BY last_call DESC")
    fun getReceivedCalls(): Flow<List<call_history_entity>>

    // Get rejected calls only
    @Query("SELECT * FROM call_history_entity WHERE last_status = 'rejected' ORDER BY last_call DESC")
    fun getRejectedCalls(): Flow<List<call_history_entity>>

    // Delete all call history
    @Query("DELETE FROM call_history_entity")
    suspend fun deleteAllCallHistory()
}
