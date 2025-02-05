package com.example.dialme.repository

import com.example.dialme.roomdb.CallHistoryDao
import com.example.dialme.roomdb.call_history_entity
import kotlinx.coroutines.flow.Flow

class CallHistoryRepository(private val callHistoryDao: CallHistoryDao) {

    // Fetch all call history records (Live Data Stream)
    val allCallHistory: Flow<List<call_history_entity>> = callHistoryDao.getAllCallHistory()

    // Fetch missed calls
    val missedCalls: Flow<List<call_history_entity>> = callHistoryDao.getMissedCalls()

    // Fetch received calls
    val receivedCalls: Flow<List<call_history_entity>> = callHistoryDao.getReceivedCalls()

    // Fetch rejected calls
    val rejectedCalls: Flow<List<call_history_entity>> = callHistoryDao.getRejectedCalls()

    // Insert a new call history record
    suspend fun insertCall(call: call_history_entity) {
        callHistoryDao.insertCall(call)
    }

    // Update an existing call history record
    suspend fun updateCall(call: call_history_entity) {
        callHistoryDao.updateCall(call)
    }

    // Delete a specific call history record
    suspend fun deleteCall(call: call_history_entity) {
        callHistoryDao.deleteCall(call)
    }

    // Get call history by contact number
    fun getCallHistoryByNumber(number: String): Flow<List<call_history_entity>> {
        return callHistoryDao.getCallHistoryByNumber(number)
    }

    // Delete all call history
    suspend fun deleteAllCallHistory() {
        callHistoryDao.deleteAllCallHistory()
    }
}
