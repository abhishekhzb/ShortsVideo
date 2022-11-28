package com.malkinfo.shotsvideo.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.andorid.shortsvideo.MyApp
import com.andorid.shortsvideo.R
import com.andorid.shortsvideo.model.Reel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.SimpleCache

class VideoAdapter(val mList :List<String>,val context:Context): RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     //   holder.setdata(mList[position])
        holder.initPlayer(mList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_video_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // var videoView: VideoView
        var playerView: PlayerView
        var title: TextView
        var desc: TextView
        var pbar: ProgressBar
        var fav :ImageView
        var isFav = false
        private lateinit var httpDataSourceFactory: HttpDataSource.Factory
        private lateinit var defaultDataSourceFactory: DefaultDataSourceFactory
        private lateinit var cacheDataSourceFactory: DataSource.Factory
        private lateinit var simpleExoPlayer: SimpleExoPlayer
        private val simpleCache: SimpleCache = MyApp.simpleCache


//        fun setdata(obj: Reel) {
//            videoView.setVideoPath(obj.reelUrl)
//            title.setText(obj.title)
//           // desc.setText(obj.reelInfo.description)
//            videoView.setOnPreparedListener { mediaPlayer ->
//                pbar.visibility = View.GONE
//                mediaPlayer.start()
//            }
//            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
//            fav.setOnClickListener {
//                if (!isFav){
//                    fav.setImageResource(R.drawable.ic_fill_favorite)
//                    isFav = true
//                }
//                else{
//                    fav.setImageResource(R.drawable.ic_favorite)
//                    isFav = false
//                }
//
//            }
//        }

        init {
           // videoView = itemView.findViewById<View>(R.id.videoView) as VideoView
            playerView = itemView.findViewById<View>(R.id.videoView) as PlayerView
            title = itemView.findViewById<View>(R.id.textVideoTitle) as TextView
            desc = itemView.findViewById<View>(R.id.textVideoDescription) as TextView
            pbar = itemView.findViewById<View>(R.id.videoProgressBar) as ProgressBar
            fav = itemView.findViewById(R.id.favorites) as ImageView
        }

         fun initPlayer(obj: String) {
           // title.setText(obj)
            httpDataSourceFactory = DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)

            defaultDataSourceFactory = DefaultDataSourceFactory(context, httpDataSourceFactory)

            cacheDataSourceFactory = CacheDataSource.Factory()
                .setCache(simpleCache)
                .setUpstreamDataSourceFactory(httpDataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)

            simpleExoPlayer = SimpleExoPlayer.Builder(context)
                .setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory)).build()

            val videoUri = Uri.parse(obj)
            val mediaItem = MediaItem.fromUri(videoUri)
            val mediaSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(mediaItem)


            pbar.visibility = View.GONE

            playerView.player = simpleExoPlayer
            simpleExoPlayer.playWhenReady = true
            simpleExoPlayer.seekTo(0, 0)
            simpleExoPlayer.repeatMode = Player.REPEAT_MODE_OFF
            simpleExoPlayer.setMediaSource(mediaSource, true)
            simpleExoPlayer.prepare()
        }
    }
}
