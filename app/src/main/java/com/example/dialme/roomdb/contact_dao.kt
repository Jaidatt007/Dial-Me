package com.example.dialme.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    // Insert a new contact
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: contact_entity)

    // Update an existing contact
    @Update
    suspend fun updateContact(contact: contact_entity)

    // Delete a contact
    @Delete
    suspend fun deleteContact(contact: contact_entity)

    // Get all contacts (sorted by name)
    @Query("SELECT * FROM contact_entity ORDER BY user_name ASC")
    fun getAllContacts(): Flow<List<contact_entity>>

    // Get a contact by ID
    @Query("SELECT * FROM contact_entity WHERE id = :contactId")
    suspend fun getContactById(contactId: Long): contact_entity?

    // Get contacts by name (for searching)
    @Query("SELECT * FROM contact_entity WHERE user_name LIKE :searchQuery")
    fun searchContacts(searchQuery: String): Flow<List<contact_entity>>

    // Get favorite contacts
    @Query("SELECT * FROM contact_entity WHERE favourite = 'true'")
    fun getFavouriteContacts(): Flow<List<contact_entity>>

    // Delete all contacts
    @Query("DELETE FROM contact_entity")
    suspend fun deleteAllContacts()
}
