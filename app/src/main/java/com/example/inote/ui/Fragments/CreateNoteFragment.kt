package com.example.inote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.inote.Model.Notes
import com.example.inote.R
import com.example.inote.ViewModel.NotesViewModel
import com.example.inote.databinding.FragmentCreateNoteBinding

import java.util.*


class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    var priority = "1"
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
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

        binding.floatingActionButton.setOnClickListener()
        {
            createNotes(it)
        }
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)
//        activity?.onBackPressed()
        return binding.root
    }

    private fun createNotes(it: View?) {
        if(binding.edtTitle.text.toString().isEmpty() || binding.edtNotes.text.toString().isEmpty())
        {
            Toast.makeText(requireContext(),"Add all Details Please :)",Toast.LENGTH_SHORT).show()
        }
        else
        {
            val title = binding.edtTitle.text.toString()
            val subtitle = binding.edtSubtitle.text.toString()
            val note = binding.edtNotes.text.toString()

            val d = Date()
            val notesDate:CharSequence  = DateFormat.format("MMMM d, yyyy",d.time)

            Log.e("Date","createNotes: "+notesDate)
            val data = Notes(null,title,subtitle,note,notesDate.toString(),priority)
            viewModel.addNotes(data)

            Toast.makeText(requireContext(),"Hack Successfully",Toast.LENGTH_SHORT).show()

            Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home)
        {
            Log.e("@@@@", "onOptionsItemSelected: Pressed Back Button")
        }
        return super.onOptionsItemSelected(item)
    }

}