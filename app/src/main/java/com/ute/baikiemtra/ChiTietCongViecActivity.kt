package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding
    // 1. Đặt biến congViecHienTai ở đây
    private lateinit var congViecHienTai: CongViec

    private val boKhoiDongChinhSua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val tenMoi = data?.getStringExtra("EXTRA_TEN_MOI") ?: ""
            val moTaMoi = data?.getStringExtra("EXTRA_MO_TA_MOI") ?: ""

            // Cập nhật lại đối tượng và giao diện sau khi chỉnh sửa
            congViecHienTai.tenCongViec = tenMoi
            congViecHienTai.moTa = moTaMoi
            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Nhận đối tượng CongViec từ Intent truyền sang
        congViecHienTai = intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
            ?: CongViec("", "")

        // Gọi hàm cập nhật giao diện
        capNhatGiaoDien()

        // Xử lý sự kiện bấm nút Chỉnh sửa
        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CV", congViecHienTai.tenCongViec)
                putExtra("EXTRA_MO_TA_CV", congViecHienTai.moTa)
            }
            boKhoiDongChinhSua.launch(intent)
        }
    }

    // 3. Đặt hàm capNhatGiaoDien() ở đây
    private fun capNhatGiaoDien() {
        binding.tvHienThiTen.text = "Tên công việc: ${congViecHienTai.tenCongViec}"
        binding.tvHienThiMoTa.text = "Mô tả: ${congViecHienTai.moTa}"
    }
}