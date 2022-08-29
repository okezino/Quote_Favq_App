package com.example.favqs.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.usecase.GetQuoteBySearchUseCase
import com.example.favqs.domain.usecase.GetQuoteByTagUseCase
import com.example.favqs.domain.usecase.GetQuoteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getQuoteBySearchUseCase: GetQuoteBySearchUseCase,
    private val getQuoteListUseCase: GetQuoteListUseCase,
    private val getQuoteByTagUseCase: GetQuoteByTagUseCase
) : ViewModel() {


    private var _quoteListData = MutableLiveData<Resource<Quotes>>()
    val quoteListData: LiveData<Resource<Quotes>> get() = _quoteListData


    fun getQuoteList() {
        _quoteListData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _quoteListData.postValue(getQuoteListUseCase.execute(Unit).data)
        }
    }

    fun getQuoteListByTag(tag: String) {
        _quoteListData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _quoteListData.postValue(getQuoteByTagUseCase.execute(tag).data)
        }
    }

    fun getQuoteListBySearch(word: String) {
        _quoteListData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _quoteListData.postValue(getQuoteBySearchUseCase.execute(word).data)
        }
    }
}