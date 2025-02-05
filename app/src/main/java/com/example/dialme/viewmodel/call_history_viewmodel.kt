package com.example.dialme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dialme.repository.CallHistoryRepository
import com.example.dialme.roomdb.call_history_entity
import com.example.dialme.roomdb.database_graph
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CallHistoryViewModel(private val callHistoryRepository: CallHistoryRepository = database_graph.callHistoryRepository) : ViewModel() {

    // Live stream of all call history records
    val allCallHistory: Flow<List<call_history_entity>> = callHistoryRepository.allCallHistory

    // Live stream of missed calls
    val missedCalls: Flow<List<call_history_entity>> = callHistoryRepository.missedCalls

    // Live stream of received calls
    val receivedCalls: Flow<List<call_history_entity>> = callHistoryRepository.receivedCalls

    // Live stream of rejected calls
    val rejectedCalls: Flow<List<call_history_entity>> = callHistoryRepository.rejectedCalls

    // Insert a new call history record
    fun insertCall(call: call_history_entity) {
        viewModelScope.launch {
            callHistoryRepository.insertCall(call)
        }
    }

    // Update an existing call history record
    fun updateCall(call: call_history_entity) {
        viewModelScope.launch {
            callHistoryRepository.updateCall(call)
        }
    }

    // Delete a specific call history record
    fun deleteCall(call: call_history_entity) {
        viewModelScope.launch {
            callHistoryRepository.deleteCall(call)
        }
    }

    // Get call history by contact number
    fun getCallHistoryByNumber(number: String): Flow<List<call_history_entity>> {
        return callHistoryRepository.getCallHistoryByNumber(number)
    }

    // Delete all call history
    fun deleteAllCallHistory() {
        viewModelScope.launch {
            callHistoryRepository.deleteAllCallHistory()
        }
    }
}
