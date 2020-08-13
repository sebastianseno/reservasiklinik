package com.dodolife.rapidnews.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    var items = emptyList<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_banner))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(int: Int) {
            containerView.apply {
                iv_banner.setImageResource(int)
            }
        }
    }
}