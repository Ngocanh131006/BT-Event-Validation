package com.ute.bt_event_validation

/**
 * Lớp tiện ích kiểm tra (validate) định dạng các trường thông tin đầu vào.
 * Sử dụng Regular Expression (Regex) kiểm tra chuẩn xác Email và Mã sinh viên.
 */
object ValidationHelper {

    private val PHONE_REGEX = Regex("^0[3789]\\d{8}$")
    // Email bắt buộc phải có đuôi @gmail.com hoặc @sv.ute.udn.vn
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@(gmail\\.com|sv\\.ute\\.udn\\.vn)$", RegexOption.IGNORE_CASE)
    // Mã sinh viên chỉ chứa ký tự số
    private val STUDENT_ID_NUMERIC_REGEX = Regex("^\\d+$")

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
     * Email: Phải thuộc miền @gmail.com hoặc @sv.ute.udn.vn
     */
    fun validateEmail(email: String): String? {
        val trimmed = email.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập email"
            !trimmed.matches(EMAIL_REGEX) ->
                "Email phải có định dạng đuôi @gmail.com hoặc @sv.ute.udn.vn"
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
     * Mã sinh viên: Không rỗng, chỉ gồm các ký tự số (tối thiểu 4 chữ số)
     */
    fun validateStudentId(studentId: String): String? {
        val trimmed = studentId.trim()
        return when {
            trimmed.isEmpty() -> "Vui lòng nhập mã sinh viên"
            !trimmed.matches(STUDENT_ID_NUMERIC_REGEX) ->
                "Mã sinh viên chỉ được chứa các ký tự số"
            trimmed.length < 4 ->
                "Mã sinh viên phải có tối thiểu 4 chữ số"
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
