package com.cb.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.cb.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Private var for view binding
    private lateinit var binding: ActivityMainBinding

    //here are some dummy values for an array pass do map of values soon//
    private val dummyvalues = arrayListOf(
        Article(
            "", "Breaking News...",
            "descriptiondisplay", "url",
            "imageurl", "publishedate",
            "content"
        ),
        Article(
            "", "Car_Crash",
            "descriptiondisplay2", "url2",
            "imageurl2", "publishedate2",
            "content2"
        ),
        Article(
            "Bill Smith", "Stocks To Buy",
            "descriptiondisplay3", "url3",
            "imageurl3", "publishedate3",
            "content3"
        ),
        Article(
            "dave jones  ", "Politics",
            "", "url4",
            "imageurl4", "publishedate4",
            "content4"
        ),
        Article(
            "some one ", "art stolen",
            "descriptiondisplay5", "url5",
            "", "publishedate5",
            "content5"
        ),

    )

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


        displayValues()

    }

    private fun displayValues() {
        val layoutGroup = binding.mainLayout
        var indexCount = 0
        for (i in layoutGroup.children) {
            //loop to apply values in i make sure to do null values if null/
                if(i is TextView)
                {
                    // set content to hardcoded values in array//
                    var content = dummyvalues[indexCount].title
                    if(dummyvalues[indexCount].author != null) {
                        content += "---" +dummyvalues[indexCount].author
                    }
                    if(sourcedummyvalues[indexCount].name != null) {
                        content += "---" +sourcedummyvalues[indexCount].name
                    }
                    i.text = content
                }
            indexCount++
        }
    }
}