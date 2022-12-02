package com.andorid.shortsvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.andorid.shortsvideo.model.reels
import com.malkinfo.shotsvideo.adapter.VideoAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    lateinit var adapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**set find id*/
        viewPager2 = findViewById(R.id.vpager)

        /**set adapter*/
        adapter = VideoAdapter(reels)
        viewPager2.adapter = adapter

    }

}