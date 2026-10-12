package com.ute.baikiemtra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.baikiemtra.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenCv = intent.getStringExtra("EXTRA_TEN_CV") ?: ""
        val moTaCv = intent.getStringExtra("EXTRA_MO_TA_CV") ?: ""

        binding.tvHienThiTen.text = "Tên công việc: $tenCv"
        binding.tvHienThiMoTa.text = "Mô tả: $moTaCv"
    }
}