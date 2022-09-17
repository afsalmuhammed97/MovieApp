package com.afsal.dev.filimapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.filimapp.R
import com.afsal.dev.filimapp.adapters.CatagoryMoviesAdapter
import com.afsal.dev.filimapp.adapters.LatestMoviesAdapter
import com.afsal.dev.filimapp.databinding.FragmentHomeBinding
import com.afsal.dev.filimapp.models.CategoriesListData
import com.afsal.dev.filimapp.network.Resource
import com.afsal.dev.filimapp.viewModel.MoviesViewModel
import com.afsal.dev.typicodapp.uttil.handleApiError

class HomeFragment : Fragment() {

   private   var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: MoviesViewModel
    private lateinit var latestMoviesAdapter: LatestMoviesAdapter
    private lateinit var catagoryAdapter:CatagoryMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentHomeBinding.inflate(inflater,container,false)

        viewModel= ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestMoviesAdapter= LatestMoviesAdapter()
        catagoryAdapter= CatagoryMoviesAdapter(){

                  Log.d("HHH","list of data ${it.categoryName}")
          ////////////////  navigateToMoveFragment()
        }
          setView()
         binding.moreBt1.setOnClickListener {
             //navigateToMoveFragment()
             }


        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
            Log.d("TTT","Catagory result ${it}") })

        viewModel.sortedMoviesData.observe(viewLifecycleOwner, Observer {

            Log.d("GGGG","sorted result ${it.toString()}")
            catagoryAdapter.differ.submitList(it)
        })

    }

    private fun setView(){
        binding.topRv.apply {
             layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter=latestMoviesAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(8)
        }


        binding.baseRv.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=catagoryAdapter

            setItemViewCacheSize(10)
        }
    }

    private fun navigateToMoveFragment(data :CategoriesListData){


      //   val action= MoviesFragmentDirections.actionNavigationImagesToImageViewFragment(data)
//      findNavController().navigate(action)
      //  findNavController().navigate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}