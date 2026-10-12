package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityChinhSuaCongViecBinding

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChinhSuaCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChinhSuaCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenHienTai = intent.getStringExtra("EXTRA_TEN_CV") ?: ""
        val moTaHienTai = intent.getStringExtra("EXTRA_MO_TA_CV") ?: ""
        val uuTienHienTai = intent.getStringExtra("EXTRA_UU_TIEN_CV") ?: "Trung bình"

        binding.etSuaTenCongViec.setText(tenHienTai)
        binding.etSuaMoTaCongViec.setText(moTaHienTai)

        // Hiển thị mức độ ưu tiên hiện tại
        when (uuTienHienTai) {
            "Thấp" -> binding.rbThap.isChecked = true
            "Cao" -> binding.rbCao.isChecked = true
            else -> binding.rbTrungBinh.isChecked = true
        }

        binding.btnLuuChinhSua.setOnClickListener {
            val tenMoi = binding.etSuaTenCongViec.text.toString().trim()
            val moTaMoi = binding.etSuaMoTaCongViec.text.toString().trim()

            // Kiểm tra tên công việc không được để trống hoặc chỉ chứa khoảng trắng
            if (tenMoi.isEmpty()) {
                binding.etSuaTenCongViec.error = "Tên công việc không được để trống hoặc chỉ chứa khoảng trắng!"
                Toast.makeText(this, "Vui lòng nhập tên công việc hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mucDoUuTienMoi = when (binding.rgMucDoUuTien.checkedRadioButtonId) {
                R.id.rbThap -> "Thấp"
                R.id.rbCao -> "Cao"
                else -> "Trung bình"
            }

            // Sử dụng Activity Result API trả về kết quả
            val resultIntent = Intent().apply {
                putExtra("EXTRA_TEN_MOI", tenMoi)
                putExtra("EXTRA_MO_TA_MOI", moTaMoi)
                putExtra("EXTRA_UU_TIEN_MOI", mucDoUuTienMoi)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}