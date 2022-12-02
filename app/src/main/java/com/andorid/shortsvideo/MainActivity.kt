package com.andorid.shortsvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        viewPager2.offscreenPageLimit = 5

        /**set adapter*/
        adapter = VideoAdapter(reels)
        viewPager2.adapter = adapter

        Log.e("CurrentView",""+viewPager2.currentItem)

        adapter.visiblePosition= 0


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i("position","$position")
                adapter.visiblePosition= position
                adapter.tempVideoView?.pause()
                adapter.notifyItemChanged(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.i("onPageScrollStateChanged" , ":: $state")
            }
        })



    }


}