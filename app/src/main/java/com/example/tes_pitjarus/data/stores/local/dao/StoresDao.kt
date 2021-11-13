package com.example.tes_pitjarus.data.stores.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import io.reactivex.Single
import kotlin.jvm.Throws

@Dao
interface StoresDao {
    @Query("SELECT * FROM stores_table")
    @Throws(Throwable::class)
    fun getStores(): Single<List<Stores>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(Throwable::class)
    fun addStores(stores: List<Stores>): List<Long>

    @Query("DELETE FROM stores_table")
    fun clearStoresTable()
}