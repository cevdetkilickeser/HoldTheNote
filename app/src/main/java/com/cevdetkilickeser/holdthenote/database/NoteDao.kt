package com.cevdetkilickeser.holdthenote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cevdetkilickeser.holdthenote.data.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * from notes ORDER BY id ASC")
    fun downloadAllNotes() : List<Note>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchQuery || '%' OR detail LIKE '%' || :searchQuery || '%'")
    suspend fun searchNote(searchQuery: String) : List<Note>


}