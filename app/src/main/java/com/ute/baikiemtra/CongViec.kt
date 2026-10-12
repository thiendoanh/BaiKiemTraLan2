package com.ute.baikiemtra

import java.io.Serializable

class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false,
    var mucDoUuTien: String = "Trung bình"
) : Serializable {

    fun layChuoiThongTin(): String {
        val chuoiTrangThai = if (trangThai) "Đã hoàn thành" else "Chưa hoàn thành"
        return "Tên: $tenCongViec | Mô tả: $moTa | Ưu tiên: $mucDoUuTien | Trạng thái: $chuoiTrangThai"
    }
}