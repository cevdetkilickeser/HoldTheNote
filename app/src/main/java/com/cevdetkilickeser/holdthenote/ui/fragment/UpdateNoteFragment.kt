package com.cevdetkilickeser.holdthenote.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cevdetkilickeser.holdthenote.R
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.databinding.FragmentUpdateNoteBinding
import com.cevdetkilickeser.holdthenote.ui.viewmodel.AddNoteViewModel
import com.cevdetkilickeser.holdthenote.ui.viewmodel.UpdateNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class UpdateNoteFragment : Fragment() {

    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var viewModel: UpdateNoteViewModel
    private lateinit var takenNote: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: UpdateNoteViewModel by viewModels()
        viewModel = tempViewModel

        val bundle: UpdateNoteFragmentArgs by navArgs()
        takenNote = bundle.note
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update_note, container, false)

        binding.updateNoteFragment = this
        binding.noteObject = takenNote

        return binding.root
    }

    fun onClickFabUpdate(view: View, title: String, detail: String){
        val date = getCurrentDateTime()
        viewModel.updateNote(takenNote.id,title,detail,date)
        val nav = UpdateNoteFragmentDirections.updateNoteToHome()
        Navigation.findNavController(view).navigate(nav)
    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}