package com.andorid.shortsvideo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.andorid.shortsvideo.model.reels
import com.malkinfo.shotsvideo.adapter.VideoAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    lateinit var adapter: VideoAdapter
    private var homeScreenCallback: HomeScreenCallback? = null

    private var videoList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
      //  startPreLoadingService()

        videoList.add("https://player.vimeo.com/external/369156564.sd.mp4?s=cfbac673bce8e336fb24b6d841765a19a7ec5e65&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/592772055.sd.mp4?s=5c5c134f640596bc0d2030ffc216c2d6c7822770&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/474065077.sd.mp4?s=165af270a1febbe4d4f69c80bee602c813f3c08f&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/481508387.sd.mp4?s=7ee6cce9757ecdc58f2252ffe7173dc2e7545c4b&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/453062027.sd.mp4?s=f5ea4f510a452b51b2e332a9b31140bd1fbaccfd&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/454811664.hd.mp4?s=62b9dd94d7b13333a06273b1573226dac1bf1aa2&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/553300988.sd.mp4?s=20b33cf35366c6c4c94ceaa3668280a90fc4781d&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/454803556.hd.mp4?s=037202ebd392eb8547212933a7e598e322d0124e&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/473378809.hd.mp4?s=e10252caf7a676c6f03affae02edb04e4fda83cd&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/372194764.sd.mp4?s=63a4d2dcbeaa20b81ef9f692bab7cb14748fb207&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/400917479.hd.mp4?s=6652a38cdc154c12e9bb6108572199c8062c4402&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/397061708.hd.mp4?s=16c60bd19ba503d7e34de6b3d6ef4fa9e4c241a9&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/369238131.sd.mp4?s=1e0a8d8dd9e2477b95b0747377f09b8a5a7d40b0&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/389258873.hd.mp4?s=9f1f1161157097c5406b49a5ebdf7aba6afd82d9&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/412230837.sd.mp4?s=2759dbac227dc9a62851271c056b2fb8f2400aec&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/283354392.sd.mp4?s=e786baf49414ff4509ebd6c685a90aabc2cea206&profile_id=165&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/396294236.hd.mp4?s=4c65605aaec216942d6e5fc4ad89b6606b642b66&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/403296618.hd.mp4?s=bb37163ecb2609631ca65f78f7eb2a8ff30678f9&profile_id=174&oauth2_token_id=57447761")
        videoList.add("https://player.vimeo.com/external/469934393.hd.mp4?s=0585a6e5c24c0d512e7bccd44b7d0063d5782334&profile_id=174&oauth2_token_id=57447761")
        // videoList.add("")

        /**set fullscreen*/
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        /**set find id*/
        viewPager2 = findViewById(R.id.vpager)

        /**set adapter*/
        adapter = VideoAdapter(videoList,this)
        viewPager2.adapter = adapter

    }

    private fun startPreLoadingService() {
        val preloadingServiceIntent = Intent(this, VideoPreLoadingService::class.java)
        preloadingServiceIntent.putStringArrayListExtra(Constants.VIDEO_LIST, videoList)
        this.startService(preloadingServiceIntent)
    }

    override fun onStart() {
        super.onStart()
        try {
            homeScreenCallback = this as HomeScreenCallback
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        homeScreenCallback = null
    }

}