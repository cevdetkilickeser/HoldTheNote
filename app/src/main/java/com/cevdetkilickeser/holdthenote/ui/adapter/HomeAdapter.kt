package com.cevdetkilickeser.holdthenote.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.holdthenote.R
import com.cevdetkilickeser.holdthenote.data.entity.Note
import com.cevdetkilickeser.holdthenote.databinding.NoteHolderBinding
import com.cevdetkilickeser.holdthenote.ui.fragment.HomeFragmentDirections

class HomeAdapter(var context: Context,
                  var noteList: List<Note>)
    : RecyclerView.Adapter<HomeAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(binding: NoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: NoteHolderBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: NoteHolderBinding = DataBindingUtil.inflate(layoutInflater, R.layout.note_holder,parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        val b = holder.binding
        b.noteObject = note

        b.cvNoteHolder.setOnClickListener {
            val nav = HomeFragmentDirections.homeToUpdateNote(note)
            Navigation.findNavController(it).navigate(nav)
        }
    }

}





/*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = NoteHolderBinding.inflate(layoutInflater,parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val note = noteList.get(position)
        val b = holder.binding

        b.cvNoteHolder.setBackgroundColor(randomColor())
        b.tvTitle.text = note.title
        b.tvTitle.isSelected = true
        b.tvDetail.text = note.detail
        b.tvDate.text = note.date
        b.tvDate.isSelected = true

        b.cvNoteHolder.setOnClickListener {
            val intent = Intent(context, AddNoteActivity::class.java)
            intent.putExtra("note",note)
            context.startActivity(intent)
        }

        b.cvNoteHolder.setOnLongClickListener {

            true
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun searchNote(searchQuery: String){
        noteList.clear()
        for (i in fulList){
            if (i.title?.lowercase()?.contains(searchQuery.lowercase()) == true ||
                i.detail?.lowercase()?.contains(searchQuery.lowercase()) == true){
                noteList.add(i)
            }
        }
        notifyDataSetChanged()
    }

    fun updateList(newList: List<Note>){
        fulList.clear()
        fulList.addAll(newList)

        noteList.clear()
        noteList.addAll(fulList)
        notifyDataSetChanged()
    }

    fun randomColor() : Int{
        val colorList = ArrayList<Int>()
        colorList.add(R.color.color1)
        colorList.add(R.color.color2)
        colorList.add(R.color.color3)
        colorList.add(R.color.color4)
        colorList.add(R.color.color5)
        colorList.add(R.color.color6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colorList.size)
        return colorList[randomIndex]
    }*/