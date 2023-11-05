package com.example.trashclassification.domain.repository

import android.content.Context
import android.graphics.Bitmap

interface TrashClassificationRepo {
    fun classification(bitmap: Bitmap) : String
}