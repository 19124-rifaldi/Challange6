package com.binar.challange5.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.binar.challange5.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private val args : com.binar.challange5.view.detail.DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DetailViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(requireActivity()){
            showLoading(it)
        }

        viewModel.loadPhoto(this,args.id.photo,binding.movieImgv)
        set()
        binding.progressBar2.visibility= View.GONE
    }

    fun set(){
        binding.apply {
            titleTv.text= args.id.title
            descTv.text= args.id.desc
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) {
            View.VISIBLE

        } else {
            View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}