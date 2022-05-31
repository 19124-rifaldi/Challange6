package com.binar.challange5.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.binar.challange5.R
import com.binar.challange5.databinding.FragmentProfileBinding
import com.binar.challange5.databinding.FragmentUpdateProfileBinding
import com.binar.challange5.room.UserModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UpdateProfileFragment : Fragment() {
    private var _binding : FragmentUpdateProfileBinding?=null
    private val binding  get()= _binding!!
    private val viewModel : UpdateProfileViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIdku()
        profile()

        binding.updateBt.setOnClickListener {
            val email = binding.emailEd.text.toString()
            val username = binding.nameEd.text.toString()
            viewModel.userData.observe(viewLifecycleOwner){data->
                val id1 = data.data?.id
                val user1 = UserModel(
                    id = id1!!,
                    email = email,
                    photo = data.data.photo.toString(),
                    username = username,
                    password =data.data?.password.toString()
                )

                if (
                    email.isEmpty() || username.isEmpty()
                ){
                    Toast.makeText(requireContext(),"jangan kosong!!",Toast.LENGTH_LONG).show()
                }else{
                    viewModel.update(user1)
                    findNavController().navigate(R.id.action_updateProfileFragment_to_profileFragment)
                }
                Log.d("test234",user1.toString())
            }

        }
    }

    private fun getIdku(){
        viewModel.getId().observe(viewLifecycleOwner){
            viewModel.getUser(it)
        }
    }

    private fun profile (){
        viewModel.userData.observe(viewLifecycleOwner){user->
            binding.apply {
                emailEd.setText(user.data?.email)
                nameEd.setText(user.data?.username)
            }
        }
    }



}