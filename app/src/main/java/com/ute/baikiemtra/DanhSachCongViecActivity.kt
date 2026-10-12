package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityDanhSachCongViecBinding

class DanhSachCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDanhSachCongViecBinding
    private lateinit var congViecHienTai: CongViec

    // Đăng ký Activity Result API để nhận thông tin chỉnh sửa trả về
    private val boKhoiDongChinhSua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val tenMoi = data?.getStringExtra("EXTRA_TEN_MOI") ?: ""
            val moTaMoi = data?.getStringExtra("EXTRA_MO_TA_MOI") ?: ""

            // Cập nhật lại đối tượng dữ liệu
            congViecHienTai.tenCongViec = tenMoi
            congViecHienTai.moTa = moTaMoi
            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận đối tượng CongViec từ Intent
        congViecHienTai = (intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec)
            ?: CongViec("Chưa có công việc", "Chưa có mô tả")

        // Hien thi giao dien ban dau
        capNhatGiaoDien()

        // Bắt sự kiện CheckBox thay đổi trạng thái hoàn thành
        binding.cbHoanThanh.setOnCheckedChangeListener { _, isChecked ->
            congViecHienTai.trangThai = isChecked
            capNhatTrangThaiUI()
        }

        // Bắt sự kiện chuyển sang trang Chỉnh sửa
        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CV", congViecHienTai.tenCongViec)
                putExtra("EXTRA_MO_TA_CV", congViecHienTai.moTa)
            }
            boKhoiDongChinhSua.launch(intent)
        }

        // Bắt sự kiện nút Quay lại
        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }

    private fun capNhatGiaoDien() {
        binding.tvTenCongViec.text = "Tên công việc: ${congViecHienTai.tenCongViec}"
        binding.tvMoTaCongViec.text = "Mô tả: ${congViecHienTai.moTa}"
        binding.cbHoanThanh.isChecked = congViecHienTai.trangThai
        capNhatTrangThaiUI()
    }

    private fun capNhatTrangThaiUI() {
        if (congViecHienTai.trangThai) {
            binding.tvTrangThai.text = "Trạng thái: Đã hoàn thành"
            binding.tvTrangThai.setTextColor(android.graphics.Color.parseColor("#388E3C"))
        } else {
            binding.tvTrangThai.text = "Trạng thái: Chưa hoàn thành"
            binding.tvTrangThai.setTextColor(android.graphics.Color.parseColor("#D32F2F"))
        }
    }
}