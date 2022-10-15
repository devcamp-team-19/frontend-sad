package com.ex.scandanggerarea.ui.feed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ex.scandanggerarea.data.Repository
import com.ex.scandanggerarea.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    var state by mutableStateOf<ResultState>(ResultState.Nothing())

    init {
        fetchFeeds()
    }

    fun fetchFeeds() {
        repo.getFeeds().doOnSubscribe {
            state = ResultState.Loading(true)
        }.subscribe({
            state = ResultState.Loading(false)
            state = ResultState.Success(it)
        }, {
            state = ResultState.Loading(false)
            state = ResultState.Error(it)
        })
    }
}