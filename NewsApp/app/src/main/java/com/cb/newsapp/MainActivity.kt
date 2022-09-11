package com.cb.newsapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cb.newsapp.databinding.ActivityMainBinding
import com.cb.newsapp.databinding.ArticleViewBinding

class MainActivity : AppCompatActivity() {
    //Private var for view binding
    private lateinit var binding: ActivityMainBinding
    lateinit var NewsRecyclerView: RecyclerView

    //you could move the sourcedummyvalues to a class for cleaner code//
    private val sourcedummyvalues = arrayListOf(
        Source(
            "cnn",
            "CNN"

        ),
        Source(
            "the_new_york_times",
            "TheNewYorkTimes"

        ),
        Source(
            "",
            ""

        ),
        Source(
            "market_watch",
            "Market Watch"

        ),
        Source(
            "Morning_Coffee",
            "Morning Coffee"

        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val dummyvalues: List<Article> = (NewsService().dummyvalues)


        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val getTitle = sharedPreference.getString("titles", null)
        //check to see if there is a save state and load if there is//

        if (getTitle != null) {
            val intent = Intent(this@MainActivity, DetailView::class.java)
            intent.putExtra("title",getTitle)
            intent.putExtra("author", sharedPreference.getString("author", ""))
            intent.putExtra("description", sharedPreference.getString("description", ""))
            intent.putExtra("content", sharedPreference.getString("content", ""))
            intent.putExtra("source", sharedPreference.getString("source", ""))
            //                intent.putExtra("image",sharedPreference.getString("image","default value"))
            startActivity(intent)

        } else {


//        dummyvalues.forEach {
//            ArticleViewBinding.inflate(layoutInflater, binding.articleContainer, true).apply {
//
//                articleName.text = it.title
//                articleAuthor.text = it.author
//                articleDescription.text = it.description
//            }
//        }
            val adapter = NewsRecyclerAdapter(dummyvalues)

            NewsRecyclerView = findViewById(R.id.NewsRecycleView)
            NewsRecyclerView.layoutManager = LinearLayoutManager(this)
            NewsRecyclerView.adapter = adapter




            adapter.setOnclickItemListener(object : NewsRecyclerAdapter.ArticleViewClickListener {
                override fun articleClicked(position: Int) {
//                Toast.makeText(this@MainActivity, "You Clickws item no $position", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, DetailView::class.java)
                    intent.putExtra("title", dummyvalues[position].title)
                    intent.putExtra("author", dummyvalues[position].author)
                    intent.putExtra("description", dummyvalues[position].description)
                    intent.putExtra("content", dummyvalues[position].content)
                    intent.putExtra("source", dummyvalues[position].publishedAt)
//                intent.putExtra("image",dummyvalues[position].imageUrl)
                    startActivity(intent)
                    //store shared prefs here

                    var editor = sharedPreference.edit()
                    editor.putString("titles", dummyvalues[position].title)
                    editor.putString("author", dummyvalues[position].author)
                    editor.putString("description", dummyvalues[position].description)
                    editor.putString("content", dummyvalues[position].content)
                    editor.putString("source", dummyvalues[position].publishedAt)
//                editor.putString("image",dummyvalues[position].imageUrl)

                    editor.commit()

                }
            })


        }
    }
}