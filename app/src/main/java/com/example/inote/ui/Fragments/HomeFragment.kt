package com.example.inote.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inote.R
import com.example.inote.ViewModel.NotesViewModel
import com.example.inote.databinding.FragmentHomeBinding
import com.example.inote.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner,
            {
//                for(i in it)
//                {
//                    Log.e("@@@", "onCreateView: ${i.title}")
//                }
                binding.rcvallNotes.layoutManager = GridLayoutManager(requireContext(),2)

                binding.rcvallNotes.adapter = NotesAdapter(requireContext(),it)
            })
        binding.btnAddNote.setOnClickListener()
        {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }
}