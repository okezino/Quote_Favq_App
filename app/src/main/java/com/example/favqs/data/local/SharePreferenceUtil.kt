package com.example.favqs.data.local

import android.content.Context
import androidx.core.content.edit

internal object SharedPreferenceUtil {
    private const val USER_TOKEN = "auth_token"

    fun saveAuthTokenInSharedPref(context: Context, token: String) {
        context.getSharedPreferences(
            "preference_file_key",
            Context.MODE_PRIVATE
        ).edit {
            putString(USER_TOKEN, token)
            apply()
        }
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences(
            "preference_file_key",
            Context.MODE_PRIVATE
        ).getString(
            USER_TOKEN, null
        )
    }
}