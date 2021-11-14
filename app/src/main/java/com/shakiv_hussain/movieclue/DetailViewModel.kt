package com.shakiv_hussain.movieclue


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakiv_hussain.movieclue.models.MovieContainer
import com.shakiv_hussain.movieclue.models.VideoContainer
import com.shakiv_hussain.movieclue.repo.Repository
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {


    private val videoContainer: MutableLiveData<VideoContainer> = MutableLiveData()
    private val similarContainer:MutableLiveData<MovieContainer> = MutableLiveData()
    fun fetchMovieVideos(id:Int){
        viewModelScope.launch {
            val movieVideoRespnse=Repository.getMovieVideos(id)
            if (movieVideoRespnse.isSuccessful){
                videoContainer.postValue(movieVideoRespnse.body())
            }
        }
    }
    fun  getVideoContainer(): LiveData<VideoContainer> {
        return videoContainer
    }

    fun getSimilar(type:String,id: Int):LiveData<MovieContainer>{
        viewModelScope.launch {
            val similarResonse=Repository.getSimilar(type,id)
            if (similarResonse.isSuccessful){
                similarContainer.postValue(similarResonse.body())
            }else{
                Log.d("tag","Response Error in getting Similar Types of movie : "+similarResonse.errorBody().toString())
            }
        }
        return similarContainer
    }


}