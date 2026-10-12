package com.ute.baikiemtra

import android.content.Intent
import android.os.Bundle
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

        binding.etSuaTenCongViec.setText(tenHienTai)
        binding.etSuaMoTaCongViec.setText(moTaHienTai)

        binding.btnLuuChinhSua.setOnClickListener {
            val tenMoi = binding.etSuaTenCongViec.text.toString()
            val moTaMoi = binding.etSuaMoTaCongViec.text.toString()

            val resultIntent = Intent().apply {
                putExtra("EXTRA_TEN_MOI", tenMoi)
                putExtra("EXTRA_MO_TA_MOI", moTaMoi)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}