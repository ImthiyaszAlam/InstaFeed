package com.imthiyas.instafeed

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instafeed.adapter.FeedAdapter
import com.imthiyas.instafeed.databinding.ActivityMainBinding
import com.imthiyas.instafeed.model.Post
import com.imthiyas.instafeed.model.PostType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FeedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val posts = createSamplePosts()

        adapter = FeedAdapter(posts)
        binding.feedRecycler.layoutManager = LinearLayoutManager(this)
        binding.feedRecycler.adapter = adapter

        // tuning for smoothness
        binding.feedRecycler.setItemViewCacheSize(15)
        binding.feedRecycler.setHasFixedSize(false)


    }

    private fun createSamplePosts(): List<Post> {
        return listOf(
            // 1. Square image
            Post(
                id = "1",
                type = PostType.IMAGE,
                imageRes = R.drawable.sample1,
                text = "Mobile #photography",
                aspectRatio = 1f // square
            ),
            // 2. Portrait image
            Post(
                id = "2",
                type = PostType.IMAGE,
                imageRes = R.drawable.tall_building,
                text = "Tall building #architecture",
                aspectRatio = 2f / 3f // portrait
            ),
            // 3. Landscape image
            Post(
                id = "3",
                type = PostType.IMAGE,
                imageRes = R.drawable.landscape_image,
                text = "Sunset Vibe #travel",
                aspectRatio = 16f / 9f // landscape
            ),
            // 4. Portrait video
            Post(
                id = "4",
                type = PostType.VIDEO,
                videoRes = R.raw.video1,
                text = "Connection 5G",
                aspectRatio = 9f / 16f // portrait video
            ),
            // 5. Landscape video
            Post(
                id = "5",
                type = PostType.VIDEO,
                videoRes = R.raw.video2,
                text = "Nature timelapse 🌲",
                aspectRatio = 16f / 9f // landscape video
            ),
            // 6. Text-only post
            Post(
                id = "6",
                type = PostType.TEXT,
                text = "Thread: Why RecyclerView performance matters ⚡"
            )
        )
    }

}