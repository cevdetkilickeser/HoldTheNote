package com.cevdetkilickeser.holdthenote.data.repo

import androidx.lifecycle.MutableLiveData
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.database.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteRepository(var notedao: NoteDao) {

    val noteListRepo : MutableLiveData<List<Note>>

    init {
        noteListRepo = MutableLiveData()
    }

    fun returnNoteListRepo() : MutableLiveData<List<Note>>{
        return noteListRepo
    }

    fun insertNote(title: String, detail: String, date: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newNote = Note(0,title,detail,date)
            notedao.insertNote(newNote)
            downloadAllNotesPassToNoteListRepo()
        }
    }

    fun updateNote(id: Int, title: String, detail: String, date: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updatedNote = Note(id, title, detail, date)
            notedao.updateNote(updatedNote)
        }
    }

    fun deleteNote(note: Note){
        val job = CoroutineScope(Dispatchers.Main).launch{
            val deletedNote = Note(note.id, "", "", "")
            notedao.deleteNote(deletedNote)
            downloadAllNotesPassToNoteListRepo()
        }
    }

    fun searchNote(searchQuery: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            noteListRepo.postValue(notedao.searchNote(searchQuery))
        }
    }

    fun downloadAllNotesPassToNoteListRepo(){
        val job = CoroutineScope(Dispatchers.IO).launch {
            noteListRepo.postValue(notedao.downloadAllNotes())
        /*val notes = notedao.downloadAllNotes()
            withContext(Dispatchers.Main) {
                noteListRepo.value = notes
            }*/
        }
    }

}