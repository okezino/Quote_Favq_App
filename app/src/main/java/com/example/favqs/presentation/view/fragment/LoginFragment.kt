package com.example.favqs.presentation.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.favqs.R
import com.example.favqs.data.local.SharedPreferenceUtil
import com.example.favqs.databinding.FragmentLoginBinding
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.model.UserLog
import com.example.favqs.presentation.viewmodel.AuthViewModel
import com.example.favqs.util.FormValidationHelper.validateMissingLoginField
import com.example.favqs.util.MISSING_FIELD
import com.example.favqs.util.SUCCESS_SESSION
import com.example.favqs.util.UiHelper.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()


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
        observeLoginResponse()
        initializeClickListener()
    }

    private fun initializeClickListener(){
        navigateToSignUp()
        getUserSession()
    }

    private fun navigateToSignUp(){
        binding.createAct.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }


    }

    private fun navigateToHomeScreen(){
            findNavController().navigate(R.id.homeFragment)

    }

    private fun getUserSession(){
        with(binding){
            loginBtn.setOnClickListener {
                val userNameEmail = loginEmailUsername.text
                val password = loginPassword.text
                val user = UserLog(userNameEmail.toString().trim(), password.toString().trim())
                if(validateMissingLoginField(user)){
                    authViewModel.loginUser(Login(user))
                    }else{
                    binding.loginProgressBar.showSnackBar(MISSING_FIELD)
                    }

            }
        }
    }

    private fun observeLoginResponse(){
        authViewModel.loginViewData.observe(viewLifecycleOwner) { resource ->

            when(resource){
                is Resource.Loading -> {
                     binding.loginProgressBar.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.loginProgressBar.visibility = View.INVISIBLE
                    binding.loginProgressBar.showSnackBar("${resource.messages}")
                }

                is Resource.Success -> {
                    binding.loginProgressBar.visibility = View.INVISIBLE
                    val response = resource.data
                    response?.userToken?.let {
                        binding.loginProgressBar.showSnackBar(SUCCESS_SESSION)
                        SharedPreferenceUtil.saveAuthTokenInSharedPref(requireContext(),it)
                        navigateToHomeScreen()
                    } ?: binding.loginProgressBar.showSnackBar("${resource.data?.message} ")

                }

            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}