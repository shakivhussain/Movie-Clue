package com.shakiv_hussain.movieclue.interfaces

import com.shakiv_hussain.movieclue.models.MovieModel

interface SearchAdapterListener {
    fun startDetailsFragment(data:MovieModel)
}