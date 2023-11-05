package com.example.trashclassification.presentation.view.classify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class UIClassifyViewModel : ViewModel() {
    private val _fabIsAllVisible = MutableLiveData<Boolean>()
    val fabIsAllVisible get() = _fabIsAllVisible

    init {
        _fabIsAllVisible.value = false
    }

    fun checkFab(){
        _fabIsAllVisible.value = !_fabIsAllVisible.value!!

    }
}