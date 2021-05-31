package com.example.gesturevideo

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class VideoAdapter(private val videoList : ArrayList<VideoItem>) : RecyclerView.Adapter<VideoViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_list_view,parent,false)
        view.setOnTouchListener(object :OnSwipeTouchListener(view.context){
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                val intent = Intent(view.context,ProfileActivity::class.java).apply {  }
                view.context.startActivity(intent)
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                Snackbar.make(view, "Subscribed", Snackbar.LENGTH_SHORT).show()
            }
        })
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoData(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private var videoView : VideoView = itemView.findViewById(R.id.videoView)
    private var progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)

    fun setVideoData(videoItem: VideoItem){
        videoView.setVideoPath(videoItem.videoURL)
        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
            it.start()
            val videoRatio = it.videoWidth/it.videoHeight.toFloat()
            val screenRatio = videoView.width/videoView.height.toFloat()
            val scale = videoRatio/screenRatio
            if(scale > 1){
                videoView.scaleX = scale
            }
            else{
                videoView.scaleY = 1/scale
            }
        }
        videoView.setOnCompletionListener {
            it.start()
        }
    }
}

