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
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.filimapp.R
import com.afsal.dev.filimapp.adapters.CatagoryMoviesAdapter
import com.afsal.dev.filimapp.adapters.LatestMoviesAdapter
import com.afsal.dev.filimapp.databinding.FragmentHomeBinding
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
        catagoryAdapter= CatagoryMoviesAdapter()
          setView()



        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
          //  Log.d("TTT","Catagory list ${it.toString()}")

            when(it){
                is Resource.Success ->{

                    val data=it.value.message()
                   Log.d("TTTT"," result code${it.value.code()}")
                   Log.d("TTTT","success result ${data.toString()}")
                }

                is Resource.Failure ->{

                          handleApiError(it)


                }

            }
        })

    }

    private fun setView(){
        binding.topRv.apply {
             layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter=latestMoviesAdapter
        }


        binding.baseRv.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=catagoryAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}