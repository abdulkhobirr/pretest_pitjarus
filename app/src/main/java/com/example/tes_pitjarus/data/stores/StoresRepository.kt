package com.example.tes_pitjarus.data.stores

import com.example.tes_pitjarus.data.stores.local.entity.Stores
import io.reactivex.Single

interface StoresRepository {
    fun getStores(): Single<List<Stores>>
    fun addStores(data: List<Stores>): List<Long>
    fun clearTable()
}