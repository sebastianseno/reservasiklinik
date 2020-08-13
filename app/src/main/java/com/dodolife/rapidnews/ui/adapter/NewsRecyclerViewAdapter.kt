package com.dodolife.rapidnews.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.network.Article
import kotlinx.android.synthetic.main.item_news_layout.view.*


class NewsRecyclerViewAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    var items = emptyList<Article>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                onClick(items[adapterPosition].url)
            }
        }

        fun bind(article: Article) {
            with(itemView) {
                bannerImage.load(article.urlToImage)
                titleText.text = article.title
                dateText.text = article.publishedAt
                sourceText.text = article.author
                description.text = article.description
            }
        }
    }

}