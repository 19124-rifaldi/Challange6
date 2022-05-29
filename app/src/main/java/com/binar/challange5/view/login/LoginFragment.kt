package com.binar.challange5.view.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.challange5.R
import com.binar.challange5.databinding.FragmentLoginBinding
import com.binar.challange5.resource.Stat
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val urepo = UserRepo.getInstance(view.context)
//        val pref = DataStoreManager(view.context)
//
//        viewModel = ViewModelProvider(
//            requireActivity(),
//            LoginFActory(urepo!!,pref)
//        )[LoginViewModel::class.java]
        checkIfLog()
        login1()
        toRegist()

    }

    private fun checkIfLog(){
        viewModel.checkIfLogin().observe(viewLifecycleOwner){id->

            if (id !=null && id!=0){
                binding.progressBarLogin.visibility =View.GONE
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                binding.progressBarLogin.visibility =View.GONE
            }

        }
    }

    private fun login1() {
        binding.loginBt.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
//
            viewModel.login(email, pass)
            Log.d("loginfrag","$email dan $pass")



        if(email == "" || pass == ""){
            Toast.makeText(requireContext(), "lengkapi data", Toast.LENGTH_LONG).show()
        }



            viewModel.loginStat.observe(viewLifecycleOwner){ user ->
            when (user.status){
                Stat.SUCCESS -> {
                    if (user.data == null) {

                        Toast.makeText(
                            requireContext(),
                            "data salah / tidak ditemukan",
                            Toast.LENGTH_LONG
                        ).show()
                    }else {
                        viewModel.saveUserDataStore( user.data.id)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                Stat.ERROR -> {
                    Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }

            }
        }

    }

    private fun toRegist(){
        binding.registerTv.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




