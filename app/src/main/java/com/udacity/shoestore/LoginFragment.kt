package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.btnLogin.setOnClickListener { v: View ->
            if (binding.txtUserName.text.isNullOrEmpty() || binding.txtUserPass.text.isNullOrEmpty()) {

                Toast.makeText(
                    activity,
                    "please enter your email and password",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                v.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        binding.btnRegister.setOnClickListener { v: View ->
            if (binding.txtUserName.text.isNullOrEmpty() || binding.txtUserPass.text.isNullOrEmpty()) {
                Toast.makeText(
                    this.activity,
                    "please enter your email and password",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                v.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        return binding.root
    }
}
