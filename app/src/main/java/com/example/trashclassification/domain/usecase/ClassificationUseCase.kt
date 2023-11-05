package com.example.trashclassification.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import com.example.trashclassification.domain.repository.TrashClassificationRepo
import com.example.trashclassification.presentation.common.resources.UIState
import javax.inject.Inject

class ClassificationUseCase @Inject constructor(private val trashRepo: TrashClassificationRepo) {
    operator fun invoke(bitmap: Bitmap) : UIState<String>{
        return try{
            val labels = trashRepo.classification(bitmap)
            UIState.Success(labels)
        }catch (e: Exception){
            UIState.Failure(e.message ?: "error during classification...")
        }
    }
}