package com.binar.challange5.view.register

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.binar.challange5.R
import com.binar.challange5.databinding.FragmentRegisterBinding
import com.binar.challange5.room.UserDatabase
import com.binar.challange5.room.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var dataBase : UserDatabase? = null
    private val viewModel : RegistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regist()

    }

    private fun regist(){
        binding.regBt.setOnClickListener{
            val client = UserModel(
                id=null,
                username = binding.usernameEt.text.toString(),
                password = binding.regpasswordEt.text.toString(),
                email = binding.regemailEt.text.toString()
            )

            if (binding.usernameEt.text.toString()==""||
                binding.regemailEt.text.toString()==""||
                binding.regpasswordEt.text.toString()=="" ||
                binding.regRepasswordEt.text.toString()=="") {
                Toast.makeText(requireContext(), "lengkapi data dulu!!!", Toast.LENGTH_LONG).show()

            }else if (binding.regpasswordEt.text.toString()!= binding.regRepasswordEt.text.toString()){
                Toast.makeText(requireContext(), "Password dan konfirmasi password harus sesuai", Toast.LENGTH_LONG).show()

            }else{
                viewModel.registTry(client)
                Toast.makeText(requireContext(), "regis berhasil", Toast.LENGTH_LONG).show()
                Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}