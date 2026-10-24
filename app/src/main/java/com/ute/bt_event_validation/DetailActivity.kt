package com.ute.bt_event_validation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ute.bt_event_validation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayData()
        setupListeners()
    }

    private fun displayData() {
        // Nhận dữ liệu thông qua đối tượng StudentInfo hoặc các extra riêng lẻ
        val student = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_STUDENT", StudentInfo::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_STUDENT") as? StudentInfo
        }

        val name = student?.fullName ?: intent.getStringExtra("EXTRA_NAME") ?: "Chưa có thông tin"
        val email = student?.email ?: intent.getStringExtra("EXTRA_EMAIL") ?: "Chưa có thông tin"
        val phone = student?.phone ?: intent.getStringExtra("EXTRA_PHONE") ?: "Chưa có thông tin"
        val msv = student?.studentId ?: intent.getStringExtra("EXTRA_MSV") ?: "Chưa có thông tin"
        val className = student?.className ?: intent.getStringExtra("EXTRA_CLASS") ?: "Chưa có thông tin"

        binding.tvFullName.text = "Họ tên: $name"
        binding.tvEmail.text = "Email: $email"
        binding.tvPhone.text = "Số điện thoại: $phone"
        binding.tvStudentId.text = "Mã sinh viên: $msv"
        binding.tvClass.text = "Lớp: $className"
    }

    private fun setupListeners() {
        // Nút xoá thông tin và quay về
        binding.btnDelete.setOnClickListener {
            Toast.makeText(this, "Đã xoá thông tin thành công!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        // Nút chuyển về trang Đăng ký
        binding.btnBackToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
