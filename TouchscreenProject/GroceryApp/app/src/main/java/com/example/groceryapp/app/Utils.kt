package com.example.groceryapp.app

import android.content.Context
import android.widget.Toast

fun Context.showToast(toastText: String) {
    Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
}