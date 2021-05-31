package com.shrikant.cogniwide.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.network.shared.domain.video.VideoItem
import com.shrikant.cogniwide.databinding.ItemHistoryBinding

class HistoryListAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<VideoItem> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.setData(list[position])
    }

    fun setItemList(list: List<VideoItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: VideoItem) {
            with(binding) {
                tvTitle.text = item.artistName

            }
        }

    }

}