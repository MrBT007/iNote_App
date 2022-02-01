package com.example.inote.ui.Fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.inote.Model.Notes
import com.example.inote.R
import com.example.inote.ViewModel.NotesViewModel
import com.example.inote.databinding.FragmentHomeBinding
import com.example.inote.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldmyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant", "RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel.getNotes().observe(viewLifecycleOwner,
            {
//                for(i in it)
//                {
//                    Log.e("@@@", "onCreateView: ${i.title}")
//                }
                oldmyNotes = it as ArrayList<Notes>
                binding.rcvallNotes.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

                adapter = NotesAdapter(requireContext(), it)
                binding.rcvallNotes.adapter = adapter
            })
        binding.btnAddNote.setOnClickListener()
        {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.filterHigh.setOnClickListener()
        {
            viewModel.getHighNotes().observe(viewLifecycleOwner,
                {
                    oldmyNotes = it as ArrayList<Notes>
                    binding.rcvallNotes.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                    adapter = NotesAdapter(requireContext(), it)
                    binding.rcvallNotes.adapter = adapter
                })
        }
        binding.filterMedium.setOnClickListener()
        {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,
                {
                    oldmyNotes = it as ArrayList<Notes>
                    binding.rcvallNotes.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                    adapter = NotesAdapter(requireContext(), it)
                    binding.rcvallNotes.adapter = adapter
                })
        }
        binding.filterLow.setOnClickListener()
        {
            viewModel.getLowNotes().observe(viewLifecycleOwner,
                {
                    oldmyNotes = it as ArrayList<Notes>
                    binding.rcvallNotes.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                    adapter = NotesAdapter(requireContext(), it)
                    binding.rcvallNotes.adapter = adapter
                })
        }
        binding.allnotes.setOnClickListener()
        {
            viewModel.getNotes().observe(viewLifecycleOwner,
                {
                    oldmyNotes = it as ArrayList<Notes>
                    binding.rcvallNotes.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                    adapter = NotesAdapter(requireContext(), it)
                    binding.rcvallNotes.adapter = adapter
                })
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchview = item.actionView as SearchView
        searchview.queryHint = "Search"
        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            // after writing query and pressed search button then it searches
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            // writing query and it searches in parallel
            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?)
    {
//        Log.e("@@@@", "NotesFiltering: $p0")

        val newFilteredList = arrayListOf<Notes>()
        for(i in oldmyNotes)
        {
            if(i.title.lowercase().contains(p0?.lowercase()!!) || i.subtitle.lowercase().contains(p0.lowercase()))
            {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}
