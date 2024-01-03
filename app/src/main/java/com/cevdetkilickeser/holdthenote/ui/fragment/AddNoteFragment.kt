package com.cevdetkilickeser.holdthenote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cevdetkilickeser.holdthenote.R
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.databinding.FragmentAddNoteBinding
import com.cevdetkilickeser.holdthenote.ui.viewmodel.AddNoteViewModel
import com.cevdetkilickeser.holdthenote.utils.DateHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: AddNoteViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note, container, false)
        binding.addNoteFragment = this
        binding.date = DateHelper.getCurrentDate()

        return binding.root
    }

    fun onClickFabDone(view: View, title: String, detail: String){
        if (title.isNotEmpty()){
            val date = DateHelper.getCurrentDate()
            viewModel.insertNote(title,detail,date)
            val nav = AddNoteFragmentDirections.addNoteToHome()
            Navigation.findNavController(view).navigate(nav)
        }else{
            Toast.makeText(requireContext(),getString(R.string.no_title),Toast.LENGTH_SHORT).show()
        }
    }

}