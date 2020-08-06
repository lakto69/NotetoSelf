package com.aml1969.notetoself

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: List<Note>)
    : RecyclerView.Adapter<NoteAdapter.ListItemHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ListItemHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem, parent, false)

        return ListItemHolder(itemView)
    }

// CODIFICAÇÃO NA MINHA VERSÃO COM ERRO:
    override fun getItemCount(): Int {
        if (noteList != null) {
            return noteList.size
        }
        // error
        return -1
    }
//// CODIFICAÇÃO NA VERSÃO OK:
//    override fun getItemCount(): Int {
//        //if (noteList != null) {
//            return noteList.size
//        //}
//        // error
//        //return -1
//    }
	
	
    override fun onBindViewHolder(
        holder: ListItemHolder, position: Int) {

        val note = noteList!![position] //VERSÃO ERRO	
//        val note = noteList[position]	//VERSÃO OK

        holder.title.text = note.title // mTitle.text = note.title

//        MODIFICAÇÃO QUE EU FIZ PRA NÃO GERAR ERRO CASO O TAMANHO
//        DA DESCRIÇÃO SEJA MENOR DO QUE 15 CARACTERES:
        // Show the first 15 characters of the actual note
        if (note.description!!.length > 15){
        holder.description.text =  //mDescription.text =
            note.description!!.substring(0, 15)
        }else{
            holder.description.text =  //mDescription.text =
                note.description!!
        }

        // What is the status of the note?
        when {
            note.idea -> holder.status.text =
                mainActivity.resources.getString(R.string.idea_text)

            note.important -> holder.status.text =
                mainActivity.resources.getString(R.string.important_text)


            note.todo -> holder.status.text =
                mainActivity.resources.getString(R.string.todo_text)
        }


    }

    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        internal var title = view.findViewById<View>(
            R.id.textViewTitle) as TextView

        internal var description = view.findViewById<View>(
            R.id.textViewDescription) as TextView

        internal var status = view.findViewById<View>(
            R.id.textViewStatus) as TextView

        init {
            view.isClickable = true
            view.setOnClickListener(this)

        }

// CODIFICAÇÃO NA MINHA VERSÃO COM ERRO:
//        IDENTIFICADO O PROBLEMA!!!!!!!
//        override fun onClick(v: View) {
//            mainActivity.showNote(adapterPosition)
//        }
// CODIFICAÇÃO NA VERSÃO OK:			
        override fun onClick(view: View) {
            mainActivity.showNote(adapterPosition)
        }

    }
}