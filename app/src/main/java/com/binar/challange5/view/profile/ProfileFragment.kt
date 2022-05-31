package com.binar.challange5.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.challange5.R
import com.binar.challange5.databinding.FragmentLoginBinding
import com.binar.challange5.databinding.FragmentProfileBinding
import com.binar.challange5.room.UserModel
import com.binar.challange5.utils.DataStoreManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val pref = DataStoreManager(view.context)
//        val factory = ProfilViewModelProvider.getInstance(view.context, pref)
//        viewModel= ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]




        viewModel.loading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        binding.logoutBt.setOnClickListener {
            viewModel.clearDataUser()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        binding.updateBt.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_updateProfileFragment)
        }

        id()
        showProfile()

    }
    private fun id (){
        viewModel.getId().observe(viewLifecycleOwner){
            viewModel.getUser(it)
        }
    }

    private fun showProfile(){
        viewModel.userData.observe(viewLifecycleOwner){data->
            binding.apply {
                emailTv.text= data.data?.email.toString()
                nameTv.text = data.data?.username

                if (data.data?.photo==null){
                    Glide.with(binding.root).load(R.drawable.ic_launcher_background)
                        .circleCrop()
                        .into(imageButton)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar3.visibility = if (isLoading) {
            View.VISIBLE

        } else {
            View.GONE
        }
    }




}