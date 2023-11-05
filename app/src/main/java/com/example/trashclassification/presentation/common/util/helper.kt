package com.example.trashclassification.presentation.common.util

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import com.example.trashclassification.presentation.common.util.Constant.BATCH_SIZE
import com.example.trashclassification.presentation.common.util.Constant.CHANNEL
import com.example.trashclassification.presentation.common.util.Constant.IMAGE_SIZE
import com.example.trashclassification.presentation.common.util.Constant.LABELS
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

object helper {

    fun imageProcessing(context: Context, uri: Uri) : Bitmap{
        val imageBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        val dimension = Integer.min(imageBitmap.width, imageBitmap.height)
        val thumbnail = ThumbnailUtils.extractThumbnail(imageBitmap, dimension, dimension)

        return Bitmap.createScaledBitmap(thumbnail, IMAGE_SIZE, IMAGE_SIZE, false)
    }

    fun result(context: Context, max: Int) : String{
        val inputString = context.assets.open(LABELS).bufferedReader().use {
            it.readText()
        }

        val listLabels = inputString.split("\n")
        return listLabels[max]
    }
}