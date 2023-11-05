package com.example.trashclassification.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.trashclassification.data.source.tflite.TrashModels
import com.example.trashclassification.domain.repository.TrashClassificationRepo
import javax.inject.Inject

class TrashClassificationRepoImpl @Inject constructor(private val models: TrashModels)
    : TrashClassificationRepo{

    override fun classification(bitmap: Bitmap) : String {
        return models.processTrashClassifications(bitmap)
    }
}