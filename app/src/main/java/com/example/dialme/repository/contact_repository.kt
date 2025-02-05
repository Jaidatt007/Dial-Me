package com.example.dialme.repository

import com.example.dialme.roomdb.ContactDao
import com.example.dialme.roomdb.contact_entity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    // Fetch all contacts (Live data stream)
    val allContacts: Flow<List<contact_entity>> = contactDao.getAllContacts()

    // Fetch favorite contacts
    val favouriteContacts: Flow<List<contact_entity>> = contactDao.getFavouriteContacts()

    // Insert a new contact
    suspend fun insertContact(contact: contact_entity) {
        contactDao.insertContact(contact)
    }

    // Update an existing contact
    suspend fun updateContact(contact: contact_entity) {
        contactDao.updateContact(contact)
    }

    // Delete a specific contact
    suspend fun deleteContact(contact: contact_entity) {
        contactDao.deleteContact(contact)
    }

    // 🔹 Get a contact by number
    suspend fun getContactByNumber(number: String): contact_entity? {
        return contactDao.getContactByNumber(number)
    }


    // Get a specific contact by ID
    suspend fun getContactById(contactId: Long): contact_entity? {
        return contactDao.getContactById(contactId)
    }

    // Search contacts by name
    fun searchContacts(searchQuery: String): Flow<List<contact_entity>> {
        return contactDao.searchContacts("%$searchQuery%") // Enables partial search
    }

    // Search contacts by number
    fun searchContactsByNumber(searchQuery: String): Flow<List<contact_entity>> {
        return contactDao.searchContactsByNumber("%$searchQuery%") // Partial matching
    }

    // Delete all contacts
    suspend fun deleteAllContacts() {
        contactDao.deleteAllContacts()
    }
}
