// adapter/FeedAdapter.kt
package com.example.instafeed.adapter

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imthiyas.instafeed.R
import com.imthiyas.instafeed.model.Post
import com.imthiyas.instafeed.model.PostType

class FeedAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_IMAGE = 1
        private const val TYPE_VIDEO = 2
        private const val TYPE_TEXT = 3
    }

    override fun getItemViewType(position: Int): Int = when (posts[position].type) {
        PostType.IMAGE -> TYPE_IMAGE
        PostType.VIDEO -> TYPE_VIDEO
        PostType.TEXT -> TYPE_TEXT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_IMAGE -> {
                val v = inflater.inflate(R.layout.item_post_image, parent, false)
                ImageVH(v)
            }

            TYPE_VIDEO -> {
                val v = inflater.inflate(R.layout.item_post_video, parent, false)
                VideoVH(v)
            }

            else -> {
                val v = inflater.inflate(R.layout.item_post_text, parent, false)
                TextVH(v)
            }
        }
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = posts[position]
        when (holder) {
            is ImageVH -> holder.bind(post)
            is VideoVH -> holder.bind(post)
            is TextVH -> holder.bind(post)
        }
    }

    // Clean up VideoView playback when view is detached
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder is VideoVH) holder.stopVideo()
        super.onViewDetachedFromWindow(holder)
    }

    // === ViewHolders ===
    inner class ImageVH(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.postImage)
        private val overlay: TextView = view.findViewById(R.id.overlayText)

        fun bind(post: Post) {
            // Set overlay text
            overlay.text = post.text ?: ""

            // Adjust ratio if provided
            post.aspectRatio?.let { ratio ->
                // ratio is width/height. To set ratio in ConstraintLayout use "W,H" format: "W,16:9"? We'll set programmatically
                val lp = image.layoutParams
                // ConstraintLayout.LayoutParams needed — cast
                (lp as? androidx.constraintlayout.widget.ConstraintLayout.LayoutParams)?.let { cLp ->
                    // ConstraintLayout dimension ratio expects "W,H" or "H,W". We'll set "W,ratio:1"
                    // But ConstraintLayout dimensionRatio expects "w,h" like "W,3:4", but easier: set "X:Y" where X:Y = width:height.
                    // We'll convert ratio to string ratioString = "${(ratio*100).toInt()}:100"
                    val rNumer = (ratio * 100).toInt()
                    val rDenom = 100
                    cLp.dimensionRatio = "$rNumer:$rDenom"
                    image.layoutParams = cLp
                }
            }

            // Load image - using Glide if available; otherwise load local drawable
            if (post.imageUrl != null) {
                // Glide recommended
                try {
                    Glide.with(image.context)
                        .load(post.imageUrl)
                        .centerCrop()
                        .into(image)
                } catch (e: Exception) {
                    // fallback
                    image.setImageResource(post.imageRes ?: android.R.color.darker_gray)
                }
            } else if (post.imageRes != null) {
                image.setImageResource(post.imageRes)
            } else {
                image.setImageResource(android.R.color.darker_gray)
            }
        }
    }

    inner class VideoVH(view: View) : RecyclerView.ViewHolder(view) {
        private val videoView: VideoView = view.findViewById(R.id.postVideo)
        private val overlay: TextView = view.findViewById(R.id.videoOverlayText)
        private val playIcon: ImageView = view.findViewById(R.id.playIcon)
        private var isPrepared = false

        fun bind(post: Post) {
            overlay.text = post.text ?: ""

            // set video uri (local raw or remote)
            val ctx = videoView.context
            val uri: Uri? = when {
                post.videoUrl != null -> Uri.parse(post.videoUrl)
                post.videoRes != null -> Uri.parse("android.resource://${ctx.packageName}/${post.videoRes}")
                else -> null
            }

            if (uri != null) {
                isPrepared = false
                videoView.setVideoURI(uri)
                videoView.setOnPreparedListener { mp ->
                    isPrepared = true
                    mp.isLooping = true
                    // auto start muted for feed-style behaviour (VideoView doesn't support volume directly)
                    try {
                        mp.setVolume(0f, 0f)
                    } catch (_: Throwable) {
                    }
                    // show play icon briefly then hide when playing
                    videoView.start()
                    playIcon.visibility = View.GONE
                }
                videoView.setOnCompletionListener {
                    // loop (we also set isLooping)
                    videoView.start()
                }

                // Toggle play/pause on click
                videoView.setOnClickListener {
                    togglePlayPause()
                }
                playIcon.setOnClickListener { togglePlayPause() }
            } else {
                // no video -> show placeholder
                playIcon.visibility = View.VISIBLE
            }
        }

        private fun togglePlayPause() {
            if (videoView.isPlaying) {
                videoView.pause()
                playIcon.visibility = View.VISIBLE
            } else {
                videoView.start()
                playIcon.visibility = View.GONE
            }
        }

        fun stopVideo() {
            if (videoView.isPlaying) {
                videoView.stopPlayback()
            } else {
                videoView.suspend()
            }
        }
    }

    inner class TextVH(view: View) : RecyclerView.ViewHolder(view) {
        private val text: TextView = view.findViewById(R.id.textOnly)
        fun bind(post: Post) {
            text.text = post.text ?: ""
        }
    }
}
