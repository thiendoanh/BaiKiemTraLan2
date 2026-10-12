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

            congViecHienTai.tenCongViec = tenMoi
            congViecHienTai.moTa = moTaMoi
            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        congViecHienTai = intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
            ?: CongViec("", "")

        capNhatGiaoDien()

        // 1. ĐẶT ĐOẠN XỬ LÝ CHECKBOX Ở ĐÂY (Bên trong onCreate)
        binding.cbHoanThanh.setOnCheckedChangeListener { _, isChecked ->
            congViecHienTai.trangThai = isChecked
            hienThiTrangThai()
        }

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CV", congViecHienTai.tenCongViec)
                putExtra("EXTRA_MO_TA_CV", congViecHienTai.moTa)
            }
            boKhoiDongChinhSua.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        binding.tvHienThiTen.text = "Tên công việc: ${congViecHienTai.tenCongViec}"
        binding.tvHienThiMoTa.text = "Mô tả: ${congViecHienTai.moTa}"
        binding.cbHoanThanh.isChecked = congViecHienTai.trangThai
        hienThiTrangThai()
    }

    // 2. ĐẶT HÀM hienThiTrangThai() Ở ĐÂY (Nằm ngoài onCreate)
    private fun hienThiTrangThai() {
        binding.tvTrangThai.text = "Trạng thái: " +
                if (congViecHienTai.trangThai) "Đã hoàn thành" else "Chưa hoàn thành"
    }
}