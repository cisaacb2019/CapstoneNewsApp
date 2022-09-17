package com.cb.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cb.newsapp.RemotesApi.GetArticle
import com.cb.newsapp.RemotesApi.RetrofitBuilder
import com.cb.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //Private var for view binding
    private lateinit var binding: ActivityMainBinding
    lateinit var NewsRecyclerView: RecyclerView
    val my_key = "d50ef362876d480eba923021703abbbc"
    //you could move the sourcedummyvalues to a class for cleaner code//
    private val sourcedummyvalues = arrayListOf(
        Source(
            "cnn",
            "CNN",
            Category.SCIENCE,
            Language.EN,
            Country.US
        ),
        Source(
            "the_new_york_times",
            "TheNewYorkTimes",
            Category.ENTERTAINMENT,
            Language.EN,
            Country.US
        ),
        Source(
            "",
            "",
            Category.GENERAL,
            Language.EN,
            Country.US
        ),
        Source(
            "market_watch",
            "Market Watch",
            Category.ENTERTAINMENT,
            Language.EN,
            Country.US
        ),
        Source(
            "Morning_Coffee",
            "Morning Coffee",
            Category.ENTERTAINMENT,
            Language.EN,
            Country.US
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val ArticleApi = RetrofitBuilder.getInstance().create(GetArticle::class.java)
        GlobalScope.launch {
            val result = ArticleApi.getArticle(my_key)
            if (result != null)
            // Checking the results
                Log.d("ayush: ", result.body().toString())
        }

        val view = binding.root
        setContentView(view)





//        val dummyvalues = NewsService().dummyvalues()

//removed shared prefrences as not needed at this time
//        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
//        val getTitle = sharedPreference.getString("titles", null)
        //check to see if there is a save state and load if there is//

//        if (getTitle != null) {
//            val intent = Intent(this@MainActivity, DetailView::class.java)
//            intent.putExtra("title",getTitle)
//            intent.putExtra("author", sharedPreference.getString("author", ""))
//            intent.putExtra("description", sharedPreference.getString("description", ""))
//            intent.putExtra("content", sharedPreference.getString("content", ""))
//            intent.putExtra("source", sharedPreference.getString("source", ""))
//            //                intent.putExtra("image",sharedPreference.getString("image","default value"))
//            startActivity(intent)
//
//        } else {

//
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
                intent.putExtra("image", dummyvalues[position].imageUrl)
                startActivity(intent)


                //store shared prefs here (removed as not needed at this point and time//
//                    var editor = sharedPreference.edit()
//                    editor.putString("titles", dummyvalues[position].title)
//                    editor.putString("author", dummyvalues[position].author)
//                    editor.putString("description", dummyvalues[position].description)
//                    editor.putString("content", dummyvalues[position].content)
//                    editor.putString("source", dummyvalues[position].publishedAt)
//                editor.putString("image",dummyvalues[position].imageUrl)
//
//                    editor.commit()

            }
        })


    }


}