package com.example.newstaskapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstaskapp.R
import com.example.newstaskapp.model.Article
import com.example.newstaskapp.util.ConstantUtil

class NewsAdapter(private val news : List<Article>, private val context: Context) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<View>(R.id.tv_title) as TextView
        private val description = itemView.findViewById<View>(R.id.tv_description) as TextView
        private val thumbnail = itemView.findViewById<View>(R.id.iv_thumbnail) as ImageView
        private val publishedAt = itemView.findViewById<View>(R.id.tv_time_source) as TextView

        fun dataBind(article: List<Article>, position: Int) {
            val agoAndSource = "${ConstantUtil.dateFormat(article[position].publishedAt)}  ${article[position].source.name}"
            title.text = article[position].title
            description.text = article[position].description
            publishedAt.text = agoAndSource
            if (article[position].urlToImage != null) {
                Glide.with(itemView.context).load(article[position].urlToImage).into(thumbnail)
            }else{
                Glide.with(itemView.context).load(R.drawable.ic_blank_img).into(thumbnail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.news_list_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dataBind(news.reversed(), position)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}
