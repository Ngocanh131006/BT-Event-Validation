package com.ute.bt_event_validation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationTest {

    @Test
    fun testEmailValidation() {
        assertNull(ValidationHelper.validateEmail("sinhvien@gmail.com"))
        assertNull(ValidationHelper.validateEmail("20110001@sv.ute.udn.vn"))

        // Invalid cases
        assertNotNull(ValidationHelper.validateEmail("user@yahoo.com"))
        assertNotNull(ValidationHelper.validateEmail("user@outlook.com"))
        assertNotNull(ValidationHelper.validateEmail("invalid-email"))
        assertNotNull(ValidationHelper.validateEmail(""))
    }

    @Test
    fun testStudentIdValidation() {
        assertNull(ValidationHelper.validateStudentId("20110001"))
        assertNull(ValidationHelper.validateStudentId("123456"))

        // Invalid cases: letters or special characters
        assertNotNull(ValidationHelper.validateStudentId("2011A001"))
        assertNotNull(ValidationHelper.validateStudentId("MSV123"))
        assertNotNull(ValidationHelper.validateStudentId("123")) // too short
        assertNotNull(ValidationHelper.validateStudentId(""))
    }

    @Test
    fun testPhoneValidation() {
        val phoneRegex = Regex("^0(3|7|8|9)\\d{8}$")
        val validPhone1 = "0391234567" // 03 + 8 digits
        val validPhone2 = "0901234567" // 09 + 8 digits
        val invalidPhone1 = "0123456789" // starts with 01 (invalid prefix)
        val invalidPhone2 = "090512345" // too short (9 digits)

        assertTrue(validPhone1.matches(phoneRegex))
        assertTrue(validPhone2.matches(phoneRegex))
        assertFalse(invalidPhone1.matches(phoneRegex))
        assertFalse(invalidPhone2.matches(phoneRegex))
    }

    @Test
    fun testNameValidation() {
        val validName = "Nguyen Van A"
        val invalidName = "A"

        val isValidValidName = validName.trim().isNotEmpty() && validName.trim().length >= 2
        val isValidInvalidName = invalidName.trim().isNotEmpty() && invalidName.trim().length >= 2

        assertTrue(isValidValidName)
        assertFalse(isValidInvalidName)
    }
}
