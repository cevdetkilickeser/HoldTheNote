package com.cevdetkilickeser.holdthenote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.data.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (var noterepo: NoteRepository) : ViewModel() {

    var noteList = MutableLiveData<List<Note>>()

    init {
        loadNotes()
        noteList = noterepo.returnNoteListRepo()
    }

    fun insertNote(title: String, detail: String, date: String){
        noterepo.insertNote(title,detail,date)
    }

    fun deleteNote(note: Note){
        noterepo.deleteNote(note)
    }

    fun searchNote(searchQuery: String){
        noterepo.searchNote(searchQuery)
        //noteList = noterepo.returnNoteListRepo()
    }

    fun loadNotes(){
        noterepo.downloadAllNotesPassToNoteListRepo()
    }

}