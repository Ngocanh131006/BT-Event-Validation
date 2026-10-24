package com.ute.bt_event_validation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.ute.bt_event_validation.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        // Xử lý sự kiện bấm nút Đăng ký
        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                val student = StudentInfo(
                    fullName = binding.etFullName.text.toString().trim(),
                    email = binding.etEmail.text.toString().trim(),
                    phone = binding.etPhone.text.toString().trim(),
                    studentId = binding.etStudentId.text.toString().trim(),
                    className = binding.etClass.text.toString().trim()
                )

                // Hiển thị Snackbar thông báo thành công
                Snackbar.make(binding.root, "Đăng ký thành công!", Snackbar.LENGTH_SHORT).show()

                // Chuyển sang màn hình Chi tiết và truyền đối tượng StudentInfo
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("EXTRA_STUDENT", student)
                    // Dự phòng thêm các extra đơn lẻ
                    putExtra("EXTRA_NAME", student.fullName)
                    putExtra("EXTRA_EMAIL", student.email)
                    putExtra("EXTRA_PHONE", student.phone)
                    putExtra("EXTRA_MSV", student.studentId)
                    putExtra("EXTRA_CLASS", student.className)
                }
                startActivity(intent)
            }
        }

        // Nút chuyển trực tiếp sang Chi tiết (theo yêu cầu mỗi màn hình có button gọi intent chuyển sang trang kia)
        binding.btnGoToDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Kiểm tra tính hợp lệ của toàn bộ form sử dụng ValidationHelper
     */
    private fun validateForm(): Boolean {
        var isValid = true

        // 1. Kiểm tra Họ và tên
        val nameError = ValidationHelper.validateFullName(binding.etFullName.text.toString())
        binding.tilFullName.error = nameError
        if (nameError != null) isValid = false

        // 2. Kiểm tra Email
        val emailError = ValidationHelper.validateEmail(binding.etEmail.text.toString())
        binding.tilEmail.error = emailError
        if (emailError != null) isValid = false

        // 3. Kiểm tra Số điện thoại
        val phoneError = ValidationHelper.validatePhone(binding.etPhone.text.toString())
        binding.tilPhone.error = phoneError
        if (phoneError != null) isValid = false

        // 4. Kiểm tra Mã sinh viên
        val msvError = ValidationHelper.validateStudentId(binding.etStudentId.text.toString())
        binding.tilStudentId.error = msvError
        if (msvError != null) isValid = false

        // 5. Kiểm tra Lớp
        val classError = ValidationHelper.validateClass(binding.etClass.text.toString())
        binding.tilClass.error = classError
        if (classError != null) isValid = false

        return isValid
    }
}
