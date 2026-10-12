package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding
    private lateinit var congViecHienTai: CongViec

    private val boKhoiDongChinhSua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val tenMoi = data?.getStringExtra("EXTRA_TEN_MOI") ?: ""
            val moTaMoi = data?.getStringExtra("EXTRA_MO_TA_MOI") ?: ""
            val uuTienMoi = data?.getStringExtra("EXTRA_UU_TIEN_MOI") ?: "Trung bình"

            congViecHienTai.tenCongViec = tenMoi
            congViecHienTai.moTa = moTaMoi
            congViecHienTai.mucDoUuTien = uuTienMoi
            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        congViecHienTai = (intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec)
            ?: CongViec("", "")

        capNhatGiaoDien()

        binding.cbHoanThanh.setOnCheckedChangeListener { _, isChecked ->
            congViecHienTai.trangThai = isChecked
            hienThiTrangThai()
        }

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CV", congViecHienTai.tenCongViec)
                putExtra("EXTRA_MO_TA_CV", congViecHienTai.moTa)
                putExtra("EXTRA_UU_TIEN_CV", congViecHienTai.mucDoUuTien)
            }
            boKhoiDongChinhSua.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        binding.tvHienThiTen.text = "Tên công việc: ${congViecHienTai.tenCongViec}"
        binding.tvHienThiMoTa.text = "Mô tả: ${congViecHienTai.moTa}"
        binding.tvHienThiMucDoUuTien.text = "Mức độ ưu tiên: ${congViecHienTai.mucDoUuTien}"
        binding.cbHoanThanh.isChecked = congViecHienTai.trangThai
        hienThiTrangThai()
    }

    private fun hienThiTrangThai() {
        binding.tvTrangThai.text = "Trạng thái: " +
                if (congViecHienTai.trangThai) "Đã hoàn thành" else "Chưa hoàn thành"
    }
}