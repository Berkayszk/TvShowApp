package com.example.tvshowapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowapp.model.PeopleImagesItem
import com.example.tvshowapp.model.TvShowItem
import com.example.tvshowapp.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository) : ViewModel() {

    private val _response = MutableLiveData<List<TvShowItem>>()
    val responseTvShow: LiveData<List<TvShowItem>>
        get() = _response

    private val _responsePeople = MutableLiveData<List<PeopleImagesItem>>()
     val responsePeople: LiveData<List<PeopleImagesItem>>
     get() = _responsePeople

    init {
        getAllPeople()
        getAllTvShows()
    }

    private fun getAllPeople() = viewModelScope.launch {
        repository.getPeople().let { response->
            if (response.isSuccessful){
                _responsePeople.postValue(response.body())
            }else{
                Log.d("tag","getAllPeople Error:${response.code()}")
            }
        }
    }

    private fun getAllTvShows() = viewModelScope.launch {
        repository.getTvShows().let {
            if (it.isSuccessful){
                _response.postValue(it.body())
            }
            else{
                Log.d("tag","getAllTvShows Error:${it.code()}")
            }
        }


    }
}
