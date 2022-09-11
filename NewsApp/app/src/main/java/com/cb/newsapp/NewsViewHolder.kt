package com.cb.newsapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView






class NewsViewHolder(itemView: View ,listener: NewsRecyclerAdapter.ArticleViewClickListener) : RecyclerView.ViewHolder(itemView)  {
        var articleAuthorView = itemView.findViewById<TextView>(R.id.articleAuthor)
        var articleTitleView = itemView.findViewById<TextView>(R.id.articleName)
        var articleDescView = itemView.findViewById<TextView>(R.id.articleDesc)
init {
    itemView.setOnClickListener {
           listener.articleClicked(absoluteAdapterPosition)
    }
}
}

//class NewsViewHolder (
//        private val newsView: NewsView
//        ): RecyclerView.ViewHolder(newsView) {
//

//
//
//
//}