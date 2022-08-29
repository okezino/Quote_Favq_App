package com.example.favqs.util

import com.example.favqs.domain.model.User
import com.example.favqs.domain.model.UserLog

object FormValidationHelper {
    fun validateMissingLoginField(user: UserLog): Boolean {
        return (user.login.isNotBlank() && user.password.isNotBlank())
    }

    fun validateMissingSignUpField(user: User): Boolean {
        return (user.login.isNotBlank() && user.password.isNotBlank() && user.email.isNotBlank())
    }
}