package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Xử lý nút "Tạo công việc"
        binding.btnTaoCongViec.setOnClickListener {
            val tenCv = binding.etTenCongViec.text.toString().trim()
            val moTaCv = binding.etMoTaCongViec.text.toString().trim()

            if (tenCv.isEmpty()) {
                binding.etTenCongViec.error = "Tên công việc không được để trống!"
                return@setOnClickListener
            }

            val doiTuongCongViec = CongViec(tenCv, moTaCv)
            val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
                putExtra("EXTRA_CONG_VIEC", doiTuongCongViec)
            }
            startActivity(intent)
        }

        binding.btnXemDanhSachCongViec.setOnClickListener {
            val tenCv = binding.etTenCongViec.text.toString().trim()
            val moTaCv = binding.etMoTaCongViec.text.toString().trim()

            if (tenCv.isEmpty()) {
                binding.etTenCongViec.error = "Vui lòng nhập tên công việc trước khi xem!"
                return@setOnClickListener
            }

            val doiTuongCongViec = CongViec(tenCv, moTaCv)
            val intent = Intent(this, DanhSachCongViecActivity::class.java).apply {
                putExtra("EXTRA_CONG_VIEC", doiTuongCongViec)
            }
            startActivity(intent)
        }
    }
}