package com.example.trashclassification.data.source.tflite

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.trashclassification.ml.ModelUnquant
import com.example.trashclassification.presentation.common.util.Constant.BATCH_SIZE
import com.example.trashclassification.presentation.common.util.Constant.CHANNEL
import com.example.trashclassification.presentation.common.util.Constant.IMAGE_SIZE
import com.example.trashclassification.presentation.common.util.Constant.LABELS_CLASS
import com.example.trashclassification.presentation.common.util.helper.result
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

class TrashModels @Inject constructor(private val modelUnquant: ModelUnquant) {
    fun processTrashClassifications(bitmap: Bitmap) : String{
        return try {
            // create input for reference
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(BATCH_SIZE, IMAGE_SIZE,
                IMAGE_SIZE, CHANNEL), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4* IMAGE_SIZE* IMAGE_SIZE*3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(IMAGE_SIZE* IMAGE_SIZE)
            bitmap.getPixels(intValues,0,bitmap.width,0,0,bitmap.width,bitmap.height)
            var pixel = 0
            for (i in 0 until IMAGE_SIZE){
                for (j in 0 until IMAGE_SIZE){
                    val values = intValues[pixel++] // RGB
                    byteBuffer.putFloat(((values shr 16) and 0xFF) * (1.0f / 255.0f))
                    byteBuffer.putFloat(((values shr 8) and 0xFF) * (1.0f / 255.0f))
                    byteBuffer.putFloat((values and 0xFF) * (1.0f / 255.0f))
                }
            }

            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = modelUnquant.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences = outputFeature0.floatArray
            var maxPos = 0
            var maxConfidence = 0.0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }

            return LABELS_CLASS[maxPos]

        }catch (e: Exception){
            Log.e(TAG, "processTrashClassifications: ${e.message}", )
            e.message ?: "error during classification"
        }finally {
            //modelUnquant.close()
        }
    }

    companion object{
        private const val TAG = "TrashModels::class"
    }
}