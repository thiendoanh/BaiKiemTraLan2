package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    private val boKhoiDongChinhSua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val tenMoi = data?.getStringExtra("EXTRA_TEN_MOI") ?: ""
            val moTaMoi = data?.getStringExtra("EXTRA_MO_TA_MOI") ?: ""

            binding.tvHienThiTen.text = "Tên công việc: $tenMoi"
            binding.tvHienThiMoTa.text = "Mô tả: $moTaMoi"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenCv = intent.getStringExtra("EXTRA_TEN_CV") ?: ""
        val moTaCv = intent.getStringExtra("EXTRA_MO_TA_CV") ?: ""

        binding.tvHienThiTen.text = "Tên công việc: $tenCv"
        binding.tvHienThiMoTa.text = "Mô tả: $moTaCv"

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CV", binding.tvHienThiTen.text.toString().replace("Tên công việc: ", ""))
                putExtra("EXTRA_MO_TA_CV", binding.tvHienThiMoTa.text.toString().replace("Mô tả: ", ""))
            }
            boKhoiDongChinhSua.launch(intent)
        }
    }
}