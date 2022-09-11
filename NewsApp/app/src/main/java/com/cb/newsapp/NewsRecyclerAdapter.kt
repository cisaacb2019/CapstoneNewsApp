package com.cb.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class NewsRecyclerAdapter (
 articleList: List<Article> = (NewsService().dummyvalues)
): RecyclerView.Adapter<NewsViewHolder>(){

lateinit var clickListener: ArticleViewClickListener
 var ArticleList = articleList

    interface ArticleViewClickListener {
        fun articleClicked(position:Int)
    }

    fun setOnclickItemListener(listener: ArticleViewClickListener){
        clickListener = listener
    }



 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
  val view = LayoutInflater.from(parent.context)
   .inflate(R.layout.article_view, parent, false)
  return NewsViewHolder(view,clickListener)
 }

 override fun getItemCount(): Int {
  return ArticleList.size
 }
 override fun onBindViewHolder(holder: NewsViewHolder, position: Int,) {
    holder.articleAuthorView.text = ArticleList[position].author
    holder.articleTitleView.text = ArticleList[position].title
    holder.articleDescView.text = ArticleList[position].description


 }



}