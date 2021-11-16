package com.example.tes_pitjarus.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import com.example.tes_pitjarus.databinding.ItemDashboardStoreBinding
import com.example.tes_pitjarus.databinding.ItemStoreBinding
import com.example.tes_pitjarus.view.fragments.DashboardStore

class DashboardStoreAdapter(
    val data: MutableList<DashboardStore> = mutableListOf()
): RecyclerView.Adapter<DashboardStoreAdapter.ViewHolder>() {
    fun setDashboardData(list: List<DashboardStore>) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardStoreAdapter.ViewHolder {
        val binding = ItemDashboardStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardStoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardStoreAdapter.ViewHolder, position: Int) {
        val item: DashboardStore = data[position]
        val dashboardViewHolder = holder as DashboardStoreViewHolder
        dashboardViewHolder.bindItem(item)
    }

    open inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class DashboardStoreViewHolder(private val binding: ItemDashboardStoreBinding): ViewHolder(binding.root) {
        fun bindItem(data: DashboardStore){
            binding.apply {
                tvTitle.text = data.name
                tvDate.text = data.date
                tvTarget.text = data.target.toString()
                tvPencapaian.text = data.pencapaian.toString()
                tvPercentage.text = data.percentage
                val context = ivDashboard.context
                mcvItemDashboard.setCardBackgroundColor(ContextCompat.getColor(context, data.color))
                if (data.drawable != null) ivDashboard.setImageResource(data.drawable)
            }
        }
    }
}