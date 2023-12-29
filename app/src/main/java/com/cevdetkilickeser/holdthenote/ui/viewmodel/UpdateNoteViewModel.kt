package com.cevdetkilickeser.holdthenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.holdthenote.data.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateNoteViewModel @Inject constructor(val noterepo: NoteRepository) : ViewModel() {

    fun updateNote(id: Int, title: String, detail: String, date: String){
        noterepo.updateNote(id,title,detail,date)
    }
}