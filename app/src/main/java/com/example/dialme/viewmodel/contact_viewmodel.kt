package com.example.dialme.viewmodel

import com.example.dialme.roomdb.database_graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dialme.repository.ContactRepository
import com.example.dialme.roomdb.contact_entity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepository: ContactRepository = database_graph.contactRepository) : ViewModel() {

    // Live stream of all contacts
    val allContacts: Flow<List<contact_entity>> = contactRepository.allContacts

    // Live stream of favorite contacts
    val favouriteContacts: Flow<List<contact_entity>> = contactRepository.favouriteContacts

    // Insert a new contact
    fun insertContact(contact: contact_entity) {
        viewModelScope.launch {
            contactRepository.insertContact(contact)
        }
    }

    // Update an existing contact
    fun updateContact(contact: contact_entity) {
        viewModelScope.launch {
            contactRepository.updateContact(contact)
        }
    }

    // Delete a specific contact
    fun deleteContact(contact: contact_entity) {
        viewModelScope.launch {
            contactRepository.deleteContact(contact)
        }
    }

    // 🔹 Get a contact by number (Suspend function, call from coroutine)
    suspend fun getContactByNumber(number: String): contact_entity? {
        return contactRepository.getContactByNumber(number)
    }

    // Get a specific contact by ID (Suspend function, call from coroutine)
    suspend fun getContactById(contactId: Long): contact_entity? {
        return contactRepository.getContactById(contactId)
    }

    // Search contacts by name
    fun searchContacts(searchQuery: String): Flow<List<contact_entity>> {
        return contactRepository.searchContacts(searchQuery)
    }

    // Search contacts by number
    fun searchContactsByNumber(searchQuery: String): Flow<List<contact_entity>> {
        return contactRepository.searchContactsByNumber(searchQuery)
    }

    // Delete all contacts
    fun deleteAllContacts() {
        viewModelScope.launch {
            contactRepository.deleteAllContacts()
        }
    }
}
