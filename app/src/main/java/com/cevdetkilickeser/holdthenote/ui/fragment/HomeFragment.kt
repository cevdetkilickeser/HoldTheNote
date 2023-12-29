package com.cevdetkilickeser.holdthenote.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cevdetkilickeser.holdthenote.R
import com.cevdetkilickeser.holdthenote.databinding.FragmentHomeBinding
import com.cevdetkilickeser.holdthenote.ui.adapter.HomeAdapter
import com.cevdetkilickeser.holdthenote.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.homeFragment = this

        viewModel.noteList.observe(viewLifecycleOwner){
            val adapter = HomeAdapter(requireContext(),it)
            binding.homeAdapter = adapter
        }

        return binding.root
    }

    fun onClickFabAdd(view:View){
        val nav = HomeFragmentDirections.homeToAddNote()
        Navigation.findNavController(view).navigate(nav)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.searchNote(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNotes()
    }
}