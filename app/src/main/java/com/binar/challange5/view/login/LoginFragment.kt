package com.binar.challange5.view.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.challange5.R
import com.binar.challange5.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by viewModels()



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

        toRegist()
        login1()

    }

    private fun login1() {
        binding.loginBt.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()

            viewModel.login(email, pass)



//            if (email == "" || pass == "") {
//                Toast.makeText(requireContext(), "lengkapi data", Toast.LENGTH_LONG).show()
//            }else if (test==0){
//                Toast.makeText(requireContext(), "data salah / tidak ditemukan", Toast.LENGTH_LONG).show()
//
//            }else{
//                if(test == 1){
//                    sharedPref?.saveKey(email,pass,log)
//                    Log.d("test",email.toString())
//                    Log.d("test2",pass.toString())
//                    Log.d("test3",log.toString())
//                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                }
//            viewModel.regist().observe(requireActivity()){
//                sharedPref?.saveKey(email,pass,it)
//            }

            viewModel.registStat().observe(requireActivity()){
                if (it == 0){

                    Toast.makeText(requireContext(), "data salah / tidak ditemukan", Toast.LENGTH_LONG).show()
                }else if(email == "" || pass == ""){
                    Toast.makeText(requireContext(), "lengkapi data", Toast.LENGTH_LONG).show()
                }else if (it == 1){
                    viewModel.regist().observe(requireActivity()){
                        val save = requireContext().getSharedPreferences("datauser",Context.MODE_PRIVATE)
                        val savep = save.edit()
                        savep.putString("username",it.toString())
                        Log.d("log",it.toString())
                        savep.apply()
                    }

//                    val log = viewModel.regist().toString()
//                    Log.d("log",log)

                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
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




