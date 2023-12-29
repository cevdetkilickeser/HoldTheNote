package com.cevdetkilickeser.holdthenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.data.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(val noterepo: NoteRepository): ViewModel() {

    fun insertNote(title: String, detail: String, date: String){
        noterepo.insertNote(title,detail,date)
    }
}