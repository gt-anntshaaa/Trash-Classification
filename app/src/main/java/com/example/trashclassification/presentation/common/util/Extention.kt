package com.example.trashclassification.presentation.common.util

import android.content.Context
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun Context.toast(message: String, duration: Int = 0){
    Toast.makeText(this, message, duration).show()
}

fun ComponentActivity.setupFullScreen(){
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

    // Configure the behavior of the hidden system bars
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    // Hide both the status bar and the navigation bar
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}