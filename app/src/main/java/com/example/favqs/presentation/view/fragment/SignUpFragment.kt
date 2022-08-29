package com.example.favqs.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.favqs.R
import com.example.favqs.databinding.FragmentSignUpBinding
import com.example.favqs.domain.model.*
import com.example.favqs.presentation.viewmodel.AuthViewModel
import com.example.favqs.util.FormValidationHelper.validateMissingSignUpField
import com.example.favqs.util.MISSING_FIELD
import com.example.favqs.util.SUCCESS_ACCOUNT
import com.example.favqs.util.UiHelper.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoginResponse()
        initializeClickListener()
        createUserSession()
    }

    private fun initializeClickListener() {
        navigateToLoginScreen()
    }

    private fun navigateToLoginScreen() {
        binding.logInSignUpScreen.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    private fun createUserSession() {
        with(binding) {
            signUpBtn.setOnClickListener {
                val userName = signUpUserName.text
                val email = signUpUserEmail.text
                val password = signUpUserPassword.text
                val user = User(
                    email.toString().trim(),
                    userName.toString().trim(),
                    password.toString().trim()
                )

                if (validateMissingSignUpField(user)) {
                    authViewModel.signUpUser(CreateUser(user))
                } else {
                    signUpUserName.showSnackBar(MISSING_FIELD)
                }

            }
        }
    }

    private fun observeLoginResponse() {
        authViewModel.signUpViewData.observe(viewLifecycleOwner) { resource ->

            when (resource) {
                is Resource.Loading -> {
                    binding.signUpProgressBar.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.signUpProgressBar.visibility = View.INVISIBLE
                    binding.signUpProgressBar.showSnackBar("${resource.messages}")
                }

                is Resource.Success -> {
                    binding.signUpProgressBar.visibility = View.INVISIBLE
                    val response = resource.data
                    response?.userToken?.let {
                        binding.signUpProgressBar.showSnackBar(SUCCESS_ACCOUNT)
                        findNavController().navigate(R.id.loginFragment)
                    } ?: binding.signUpProgressBar.showSnackBar("${resource.data?.message}")

                }
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}