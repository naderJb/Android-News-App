package com.finder.androidnewsapp.articles.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.base.utils.DateUtils
import com.finder.androidnewsapp.databinding.ItemArticleBinding
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var itemListener: ((ArticleModel) -> Unit)? = null
    private val articles: ArrayList<ArticleModel> = arrayListOf()

    fun setOnItemClickListener(listener: (ArticleModel) -> Unit) {
        itemListener = listener
    }

    fun addData(repos: List<ArticleModel>) {
        articles.clear()
        articles.addAll(ArrayList(repos))
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articles.size

    inner class NewsViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ArticleModel) {
            binding.article = articleModel
            binding.root.setOnClickListener { itemListener?.invoke(articleModel) }
            binding.tvPublishAt.text = DateUtils.formatDate(articleModel.publishedAt)
        }

    }

}
