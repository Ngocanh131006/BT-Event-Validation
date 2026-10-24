package com.ute.bt_event_validation

import java.io.Serializable

/**
 * Model chứa dữ liệu đăng ký của sinh viên.
 * Kế thừa Serializable để dễ dàng truyền nguyên đối tượng qua Intent.
 */
data class StudentInfo(
    val fullName: String,
    val email: String,
    val phone: String,
    val studentId: String,
    val className: String
) : Serializable
