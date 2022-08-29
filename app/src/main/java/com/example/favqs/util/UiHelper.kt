package com.example.favqs.util

import android.view.View
import com.example.favqs.domain.model.User
import com.example.favqs.domain.model.UserLog
import com.google.android.material.snackbar.Snackbar

object UiHelper {

    fun View.showSnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .show()
    }
}