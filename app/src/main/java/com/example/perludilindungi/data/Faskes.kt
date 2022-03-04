package com.example.perludilindungi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.perludilindungi.models.FaskesDetailData

@Entity(tableName = "faskes")
data class Faskes (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "kode")
    var kode: String = "",
    @ColumnInfo(name = "nama")
    val nama: String = "",
    @ColumnInfo(name = "alamat")
    var alamat: String = "",
    @ColumnInfo(name = "latitude")
    var latitude: String = "",
    @ColumnInfo(name = "longitude")
    var longitude: String = "",
    @ColumnInfo(name = "telp")
    var telp: String = "",
    @ColumnInfo(name = "jenis_faskes")
    var jenis_faskes: String = "",
    @ColumnInfo(name = "status")
    var status: String = "",
)