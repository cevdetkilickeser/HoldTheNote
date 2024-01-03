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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.holdthenote.R
import com.cevdetkilickeser.holdthenote.databinding.FragmentHomeBinding
import com.cevdetkilickeser.holdthenote.ui.adapter.HomeAdapter
import com.cevdetkilickeser.holdthenote.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.homeFragment = this
        searchView = binding.searchView

        viewModel.noteList.observe(viewLifecycleOwner){
            homeAdapter = HomeAdapter(requireContext(),it)
            binding.homeAdapter = homeAdapter
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNote(newText)
                return true
            }

        })


        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedNote = homeAdapter.noteList[position]
                viewModel.deleteNote(deletedNote)

                Snackbar.make(requireView(),getString(R.string.deleteLogcat), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo),
                        View.OnClickListener {
                            viewModel.insertNote(deletedNote.title, deletedNote.detail, deletedNote.date)
                        }
                    ).show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.recyclerView)

        return binding.root
    }

    fun onClickFabAdd(view:View){
        val nav = HomeFragmentDirections.homeToAddNote()
        Navigation.findNavController(view).navigate(nav)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNotes()
    }
}