package com.example.tes_pitjarus.data.stores.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "stores_table")
@Parcelize
data class Stores(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val store_id: String,
    val store_name: String,
    val address: String,
    val dc_id: String,
    val dc_name: String,
    val account_id: String,
    val account_name: String,
    val subchannel_id: String,
    val subchannel_name: String,
    val channel_id: String,
    val channel_name: String,
    val area_id: String,
    val area_name: String,
    val region_id: String,
    val region_name: String,
    val latitude: String,
    val longitude: String
) : Parcelable