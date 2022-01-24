package com.example.inote.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inote.Model.Notes
import com.example.inote.R
import com.example.inote.databinding.NoteSampleBinding

class NotesAdapter(var requireContext: Context,var noteslist: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesviewholder>()
{
    class notesviewholder(val binding: NoteSampleBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewholder
    {
       return notesviewholder(NoteSampleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= noteslist.size

    override fun onBindViewHolder(holder: notesviewholder, position: Int)
    {
        val data = noteslist[position]
        holder.binding.sampleTitle.text = data.title
        holder.binding.sampleSubtitle.text = data.subtitle
        holder.binding.sampleDate.text = data.date
        when(data.priority)
        {
            "1"->{
                holder.binding.samplePriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.samplePriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.samplePriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
    }

}