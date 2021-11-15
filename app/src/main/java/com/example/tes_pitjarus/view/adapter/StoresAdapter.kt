package com.example.tes_pitjarus.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import com.example.tes_pitjarus.databinding.ItemStoreBinding

class StoresAdapter (
    val data: MutableList<Stores> = mutableListOf(),
    val listener: OnStoreClicked
    ): RecyclerView.Adapter<StoresAdapter.ViewHolder>() {
    fun setStoresData(list: List<Stores>) {
        if (data.isNotEmpty()) {
            data.clear()
        }
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData(){
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresAdapter.ViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoresAdapter.ViewHolder, position: Int) {
        val item: Stores = data[position]
        val storeViewHolder = holder as StoreViewHolder
        storeViewHolder.bindStore(item)
    }

    open inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class StoreViewHolder(private val binding: ItemStoreBinding): ViewHolder(binding.root) {
        fun bindStore(data: Stores){
            binding.apply {
                tvNamaToko.text = data.store_name
                mcvStore.setOnClickListener {
                    listener.onStoreClicked(data)
                }
            }
        }
    }

    interface OnStoreClicked{
        fun onStoreClicked(data: Stores)
    }
}