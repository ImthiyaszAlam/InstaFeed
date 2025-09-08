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
            // 6. Text-only post
            Post(
                id = "4",
                type = PostType.TEXT,
                text = "Thread: Why RecyclerView performance matters "
            ),

            Post(
                id = "5",
                type = PostType.VIDEO,
                videoRes = R.raw.video_3,
                text = "Nature ",
                aspectRatio = 16f / 9f // landscape video
            ),

            Post(
                id = "6",
                type = PostType.VIDEO,
                videoRes = R.raw.video4,
                text = "Nature timelapse",
                aspectRatio = 16f / 9f // landscape video
            ),

            Post(
                id = "7",
                type = PostType.VIDEO,
                videoRes = R.raw.video6,
                text = "Nature Tigers",
                aspectRatio = 16f / 9f // landscape video
            ),
            // 1. Square image
            Post(
                id = "8",
                type = PostType.IMAGE,
                imageRes = R.drawable.sample1,
                text = "Mobile #photography",
                aspectRatio = 1f // square
            ),
            // 2. Portrait image
            Post(
                id = "9",
                type = PostType.IMAGE,
                imageRes = R.drawable.tall_building,
                text = "Tall building #architecture",
                aspectRatio = 2f / 3f // portrait
            ),
            // 3. Landscape image
            Post(
                id = "10",
                type = PostType.IMAGE,
                imageRes = R.drawable.landscape_image,
                text = "Sunset Vibe #travel",
                aspectRatio = 16f / 9f // landscape
            ),
            // 6. Text-only post
            Post(
                id = "11",
                type = PostType.TEXT,
                text = "Thread: Why RecyclerView performance matters "
            ),

            Post(
                id = "12",
                type = PostType.VIDEO,
                videoRes = R.raw.video_3,
                text = "Nature ",
                aspectRatio = 16f / 9f // landscape video
            ),

            Post(
                id = "13",
                type = PostType.VIDEO,
                videoRes = R.raw.video4,
                text = "Nature timelapse",
                aspectRatio = 16f / 9f // landscape video
            ),

            Post(
                id = "14",
                type = PostType.VIDEO,
                videoRes = R.raw.video6,
                text = "Nature Tigers",
                aspectRatio = 16f / 9f // landscape video
            ),
        )
    }

}