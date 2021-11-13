package com.example.tes_pitjarus.data.stores

import com.example.tes_pitjarus.data.stores.local.dao.StoresDao
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import io.reactivex.Single

class StoresDataStore (private val localDb: StoresDao): StoresRepository {
    override fun getStores(): Single<List<Stores>> {
        return localDb.getStores()
    }

    override fun addStores(data: List<Stores>): List<Long> {
        return localDb.addStores(data)
    }

    override fun clearTable() {
        localDb.clearStoresTable()
    }
}