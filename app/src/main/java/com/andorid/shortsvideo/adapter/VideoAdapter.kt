package com.malkinfo.shotsvideo.adapter

import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.andorid.shortsvideo.R
import com.andorid.shortsvideo.model.Reel

class VideoAdapter(private val mReelLst :List<Reel>): RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    var visiblePosition: Int = 0

    var tempVideoView: VideoView? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setdata(mReelLst[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_video_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mReelLst.size
    }



    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.e("onViewAttachedToWindow","onViewAttachedToWindow")
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.e("onViewDetachedFromWindow","onViewDetachedFromWindow")
    }




    // ViewHolder, inner class
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView
        var title: TextView
        var desc: TextView
        var pbar: ProgressBar
        var fav :ImageView
        var isFav = false

        fun startVideoFromTemp(videoV: VideoView) {
            tempVideoView = videoV
            tempVideoView?.start()
        }


        fun setdata(reelData: Reel, position: Int) {
            title.text = reelData.title

            //video
            videoView.setVideoPath(reelData.reelUrl)

            if(visiblePosition == position && reelData.isMediaReady) {
                startVideoFromTemp(videoView)
            } else if(visiblePosition != position && reelData.isMediaReady){
                videoView.pause()
            }

            videoView.setOnPreparedListener(object : OnPreparedListener{
                override fun onPrepared(p0: MediaPlayer?) {
                    pbar.visibility = View.GONE
                    reelData.isMediaReady = true;
                    if(visiblePosition == position) {
                        startVideoFromTemp(videoView)
                        reelData.isPlaying = true
                    } else {
                        reelData.isPlaying = false
                    }
                }
            })

//            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }

        }


        init {
            videoView = itemView.findViewById<View>(R.id.videoView) as VideoView
            title = itemView.findViewById<View>(R.id.textVideoTitle) as TextView
            desc = itemView.findViewById<View>(R.id.textVideoDescription) as TextView
            pbar = itemView.findViewById<View>(R.id.videoProgressBar) as ProgressBar
            fav = itemView.findViewById(R.id.favorites) as ImageView
        }
    }
}
