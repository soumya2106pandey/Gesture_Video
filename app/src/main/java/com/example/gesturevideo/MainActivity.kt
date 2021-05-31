package com.example.gesturevideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var videosList : ArrayList<VideoItem>
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var rootLayout : ConstraintLayout
    private lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        videosList = ArrayList()
        videoAdapter = VideoAdapter(videosList)
        setVideoUrl()
        viewPager = findViewById(R.id.videosViewPager)
        viewPager.adapter = videoAdapter
    }


    private fun setVideoUrl() {
       videosList.addAll(
           listOf(VideoItem(getString(R.string.videoUrl1)),
                   VideoItem(getString(R.string.videoUrl2)),
                   VideoItem(getString(R.string.videoURl3)),
                   VideoItem(getString(R.string.videoUrl4)),
                   VideoItem(getString(R.string.videoUrl5)),
                   VideoItem(getString(R.string.videoUrl6)))
       )
    }
}