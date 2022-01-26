package com.example.inote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.inote.Model.Notes
import com.example.inote.R
import com.example.inote.ViewModel.NotesViewModel
import com.example.inote.databinding.FragmentEditBinding
import java.util.*


class EditFragment : Fragment()
{
    var priority = "1"
    lateinit var binding:FragmentEditBinding
    val viewModel: NotesViewModel by viewModels()
    val notes by navArgs<EditFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater,container,false)
        binding.edtTitle.setText(notes.data.title)
        binding.edtSubtitle.setText(notes.data.subtitle)
        binding.edtNotes.setText(notes.data.note)

        binding.pGreen.setOnClickListener()
        {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pYellow.setOnClickListener()
        {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener()
        {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        when(notes.data.priority)
        {
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2"->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3"->{
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }
        binding.floatingActionButton.setOnClickListener()
        {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?)
    {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val note = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate:CharSequence  = DateFormat.format("MMMM d, yyyy",d.time)

        Log.e("Date","createNotes: "+notesDate)
        val data = Notes(notes.data.id,title,subtitle,note,notesDate.toString(),priority)
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Hack Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)
    }

}