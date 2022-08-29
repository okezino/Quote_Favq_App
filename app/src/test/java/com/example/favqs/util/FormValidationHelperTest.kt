package com.example.favqs.util

import com.example.favqs.domain.model.User
import com.example.favqs.domain.model.UserLog
import com.example.favqs.util.FormValidationHelper.validateMissingLoginField
import com.example.favqs.util.FormValidationHelper.validateMissingSignUpField
import org.junit.Assert.*

import org.junit.Test

class FormValidationHelperTest {
    var loginUser = UserLog(""," ")
    var loginUserSec = UserLog("mena"," ")
    var loginUserThird = UserLog("mena","mena123")

    private val createUser = User("", "", "")
    private val createUserSec = User("mena4life@gmail.com", "", "")
    private val createUserThird = User("mena4life@gmail.com", "mena", "")
    private val createUserForth = User("mena4life@gmail.com", "mena", "1234")

    @Test
    fun `get false on blank login User Data`() {
        assertEquals(validateMissingLoginField(loginUser), false)
    }

    @Test
    fun `get false on any blank property in login User Data`() {
        assertEquals(validateMissingLoginField(loginUserSec), false)
    }

    @Test
    fun `get true on  non blank property in login User Data`() {
        assertEquals(validateMissingLoginField(loginUserThird), true)
    }

    @Test
    fun `get false on all blank properties Create User Data`() {
        assertEquals(validateMissingSignUpField(createUser), false)
    }

    @Test
    fun `get false on any One blank property in Create User Data`() {
        assertEquals(validateMissingSignUpField(createUserThird), false)
    }

    @Test
    fun `get false on any Two blank property in Create User Data`() {
        assertEquals(validateMissingSignUpField(createUserSec), false)
    }

    @Test
    fun `get true on any Two blank property in Create User Data`() {
        assertEquals(validateMissingSignUpField(createUserForth), true)
    }
}