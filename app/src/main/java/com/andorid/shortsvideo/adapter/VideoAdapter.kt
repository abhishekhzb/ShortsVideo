package com.malkinfo.shotsvideo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.andorid.shortsvideo.R
import com.andorid.shortsvideo.model.Reel


class VideoAdapter(val mList :List<Reel>): RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setdata(mList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_video_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView
        var title: TextView
        var desc: TextView
        var pbar: ProgressBar
        var fav :ImageView
        var isFav = false
        fun setdata(obj: Reel) {
            videoView.setVideoPath(obj.reelUrl)
            title.setText(obj.title)
           // desc.setText(obj.reelInfo.description)
            videoView.setOnPreparedListener { mediaPlayer ->
                pbar.visibility = View.GONE
                mediaPlayer.start()
            }
            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
            fav.setOnClickListener {
                if (!isFav){
                    fav.setImageResource(R.drawable.ic_fill_favorite)
                    isFav = true
                }
                else{
                    fav.setImageResource(R.drawable.ic_favorite)
                    isFav = false
                }

            }

            // for play and pause
            videoView.setOnTouchListener(OnTouchListener { view, motionEvent ->
                if (videoView.isPlaying()) {
                    videoView.pause()
                    false
                } else {
                    videoView.start()
                    false
                }
            })
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
