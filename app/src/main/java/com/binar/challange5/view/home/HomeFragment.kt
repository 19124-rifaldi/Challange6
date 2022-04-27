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
import androidx.recyclerview.widget.LinearLayoutManager

import com.binar.challange5.databinding.FragmentHomeBinding
import com.binar.challange5.model.Result
import com.binar.challange5.view.adapter.HomeAdapter


class HomeFragment : Fragment() {
    private val viewModel : HomeViewModel by viewModels()
    private lateinit var adapter1: HomeAdapter
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    lateinit var login : SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
//
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val getdata = SharedPref(view.context)
        login=requireContext().getSharedPreferences("datauser",Context.MODE_PRIVATE)
        val getdata = login.getString("username","")

        binding.usernameTv.text = "helo , ${getdata}"

        viewModel.movie.observe(requireActivity()) {
            showList(it)
        }
        viewModel.loading.observe(requireActivity()){
            showLoading(it)
        }
        login=requireContext().getSharedPreferences("datauser",Context.MODE_PRIVATE)

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
