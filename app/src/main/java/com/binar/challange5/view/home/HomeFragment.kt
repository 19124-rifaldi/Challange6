package com.binar.challange5.view.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challange5.App
import com.binar.challange5.MainActivity
import com.binar.challange5.R

import com.binar.challange5.databinding.FragmentHomeBinding
import com.binar.challange5.model.Result
import com.binar.challange5.utils.DataStoreManager
import com.binar.challange5.view.adapter.HomeAdapter


class HomeFragment : Fragment() {
    lateinit var viewModel : HomeViewModel
    private lateinit var adapter1: HomeAdapter
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    lateinit var login : SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),HomeFactory.getInstance(
                view.context,
                (activity?.application as App).repository,
                DataStoreManager(view.context)
            ))[HomeViewModel::class.java]



        getUser()

        viewModel.movie.observe(viewLifecycleOwner) {
            showList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        binding.logout.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }


    }

    private fun getUser(){
        viewModel.getIdUser().observe(viewLifecycleOwner){
            viewModel.User(it)
            viewModel.user.observe(viewLifecycleOwner){user->
                binding.usernameTv.text = "helo , ${user.data?.username}"
            }

        }

    }



    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE

        } else {
            View.GONE
        }
    }

    private fun showList(data : List<Result>){

        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recycler.layoutManager = layoutManager
        adapter1 = HomeAdapter(data)
        adapter1.submitData(data)
        binding.recycler.adapter = adapter1
        Log.d("homefrag","harusnya bisa")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
