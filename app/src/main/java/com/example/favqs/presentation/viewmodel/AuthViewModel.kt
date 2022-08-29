package com.example.favqs.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.CreateUser
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.usecase.LoginUseCase
import com.example.favqs.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private var _loginViewData = MutableLiveData<Resource<AuthData>>()
    val loginViewData: LiveData<Resource<AuthData>> get() = _loginViewData

    private var _signUpViewData = MutableLiveData<Resource<AuthData>>()
    val signUpViewData: LiveData<Resource<AuthData>> get() = _signUpViewData


    fun loginUser(user: Login) {
        _loginViewData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _loginViewData.postValue(loginUseCase.execute(user).data)
        }
    }

    fun signUpUser(user: CreateUser) {
        _signUpViewData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _signUpViewData.postValue(signUpUseCase.execute(user).data)
        }
    }
}