package com.example.trashclassification.presentation.view.classify

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trashclassification.domain.usecase.ClassificationUseCase
import com.example.trashclassification.presentation.common.resources.UIState
import com.example.trashclassification.presentation.common.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassificationViewModel @Inject constructor
    (private val classificationUseCase: ClassificationUseCase): ViewModel() {

    private val _image = MutableLiveData<Uri>()
    val image get() = _image

    private val _classification = SingleLiveEvent<UIState<String>>()
    val classification get() = _classification

    fun doClassification(images: Bitmap){
        _classification.value = classificationUseCase(images)
    }


    fun setImage(uri: Uri){
        _image.value = uri
    }
}