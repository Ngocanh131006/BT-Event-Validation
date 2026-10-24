package com.ute.bt_event_validation

import android.util.Patterns

/**
 * Lớp tiện ích kiểm tra (validate) định dạng các trường thông tin đầu vào.
 * Sử dụng Regular Expression (Regex) và Patterns của Android.
 */
object ValidationHelper {

    private val PHONE_REGEX = Regex("^0[3789]\\d{8}$")
    private val STUDENT_ID_REGEX = Regex("^[A-Za-z0-9]{3,15}$")

    /**
     * Họ và tên: Không rỗng, tối thiểu 2 ký tự
     */
    fun validateFullName(name: String): String? {
        val trimmed = name.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập họ và tên"
            trimmed.length < 2 -> "Họ và tên quá ngắn (tối thiểu 2 ký tự)"
            else -> null
        }
    }

    /**
     * Email: Đúng định dạng email chuẩn và có dấu chấm tên miền
     */
    fun validateEmail(email: String): String? {
        val trimmed = email.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập email"
            !Patterns.EMAIL_ADDRESS.matcher(trimmed).matches() || !trimmed.contains(".") ->
                "Email không hợp lệ (ví dụ: sinhvien@ute.edu.vn)"
            else -> null
        }
    }

    /**
     * Số điện thoại: 10 chữ số, bắt đầu bằng các đầu số di động phổ biến tại VN (03, 07, 08, 09)
     */
    fun validatePhone(phone: String): String? {
        val trimmed = phone.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập số điện thoại"
            !trimmed.matches(PHONE_REGEX) ->
                "SĐT gồm 10 số, bắt đầu bằng 03, 07, 08 hoặc 09"
            else -> null
        }
    }

    /**
     * Mã sinh viên: Không rỗng, từ 3-15 ký tự chữ và số
     */
    fun validateStudentId(studentId: String): String? {
        val trimmed = studentId.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập mã sinh viên"
            !trimmed.matches(STUDENT_ID_REGEX) ->
                "MSV từ 3-15 ký tự chữ và số, không chứa ký tự đặc biệt"
            else -> null
        }
    }

    /**
     * Lớp học: Không được để trống
     */
    fun validateClass(className: String): String? {
        val trimmed = className.trim()
        return if (trimmed.isEmpty()) "Vui lòng nhập tên lớp" else null
    }
}
