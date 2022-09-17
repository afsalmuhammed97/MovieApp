package com.afsal.dev.filimapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.afsal.dev.filimapp.R
import com.afsal.dev.filimapp.adapters.GalleryAdapter
import com.afsal.dev.filimapp.databinding.FragmentMoviesBinding
import com.afsal.dev.filimapp.viewModel.MoviesViewModel


class MoviesFragment : Fragment() {


    private var _binding: FragmentMoviesBinding?= null
        val binding get() = _binding !!
    private lateinit var galleryAdapter: GalleryAdapter
   private lateinit var viewModel: MoviesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding=FragmentMoviesBinding.inflate(inflater,container,false)

       viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

     return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryAdapter=GalleryAdapter()
        setView()


        viewModel.selectdMovies.observe(requireActivity(), Observer {
            Log.d("GGG","Selected movies ${it.toString()}")

            galleryAdapter.differ.submitList(it)
        })
    }


    private fun setView(){

        binding.moviesRv.apply {
                layoutManager=GridLayoutManager(context,3)
                adapter=galleryAdapter

            }

    }

    override fun onDestroy() {
        super.onDestroy()

        _binding=null
    }

}