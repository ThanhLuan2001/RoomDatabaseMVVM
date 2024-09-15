package com.example.room_mvvm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room_mvvm.R
import com.example.room_mvvm.model.NoteModel

class NoteAdapter(
    private val context: Context,
    private val onUpdate: (NoteModel) -> Unit,
    private val onDelete: (NoteModel) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var listNote: List<NoteModel> = listOf()


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvDescreption: TextView = itemView.findViewById(R.id.tv_descreption)
        private val tvDelete: TextView = itemView.findViewById(R.id.tv_delete)
        private val item: LinearLayout = itemView.findViewById(R.id.layout_item)


        fun onBind(noteModel: NoteModel) {

            tvTitle.text = noteModel.title
            tvDescreption.text = noteModel.description

            tvDelete.setOnClickListener {
                onDelete(noteModel)
            }
            item.setOnClickListener {
                onUpdate(noteModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = listNote.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListNote(listNote: List<NoteModel>) {
        this.listNote = listNote
        notifyDataSetChanged()
    }
}