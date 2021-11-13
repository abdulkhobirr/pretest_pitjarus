package com.example.tes_pitjarus.data.model

import com.example.tes_pitjarus.data.stores.local.entity.Stores
import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("stores") val storesData: List<StoresData>
) {
    fun toStores(): List<Stores> {
        val list = mutableListOf<Stores>()
        this.storesData.forEachIndexed { index, it ->
            list.add(
                    Stores(
                            index,
                            it.store_id,
                            it.store_name,
                            it.address,
                            it.dc_id,
                            it.dc_name,
                            it.account_id,
                            it.account_name,
                            it.subchannel_id,
                            it.subchannel_name,
                            it.channel_id,
                            it.channel_name,
                            it.area_id,
                            it.area_name,
                            it.region_id,
                            it.region_name,
                            it.latitude,
                            it.longitude
                    )
            )
        }
        return list
    }
}

data class StoresData(
    @SerializedName("store_id") val store_id: String,
    @SerializedName("store_name") val store_name: String,
    @SerializedName("address") val address: String,
    @SerializedName("dc_id") val dc_id: String,
    @SerializedName("dc_name") val dc_name: String,
    @SerializedName("account_id") val account_id: String,
    @SerializedName("account_name") val account_name: String,
    @SerializedName("subchannel_id") val subchannel_id: String,
    @SerializedName("subchannel_name") val subchannel_name: String,
    @SerializedName("channel_id") val channel_id: String,
    @SerializedName("channel_name") val channel_name: String,
    @SerializedName("area_id") val area_id: String,
    @SerializedName("area_name") val area_name: String,
    @SerializedName("region_id") val region_id: String,
    @SerializedName("region_name") val region_name: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)