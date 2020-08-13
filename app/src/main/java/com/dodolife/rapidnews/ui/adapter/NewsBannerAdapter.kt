package com.dodolife.rapidnews.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.network.Article
import com.dodolife.rapidnews.utils.inflate
import com.dodolife.rapidnews.utils.toDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_banner_news.view.*

class NewsBannerAdapter : RecyclerView.Adapter<NewsBannerAdapter.ViewHolder>() {

    var items = emptyList<Article>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_banner_news))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(article: Article) {
            containerView.apply {
                bannerImage.load(article.urlToImage)
                titleText.text = article.title
                dateText.text = article.publishedAt.toDate("EEEE, dd MMM yyyy")
                sourceText.text = article.source.name
            }
        }
    }
}