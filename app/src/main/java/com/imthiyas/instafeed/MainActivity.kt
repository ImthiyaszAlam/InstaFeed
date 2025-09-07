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
            // image (square)
            Post(
                id = "1",
                type = PostType.IMAGE,
                imageRes = R.drawable.sample1,
                text = "Sunset vibes #photography",
                aspectRatio = 1f  // square
            ),
            // portrait image
            Post(
                id = "2",
                type = PostType.IMAGE,
                imageRes = R.drawable.sample1,
                text = "Tall building",
                aspectRatio = 0.66f // width/height (narrow wide)
            ),
            // video (local raw)
            Post(
                id = "3",
                type = PostType.VIDEO,
                videoRes = R.raw.video1,
                text = "Skateboard tricks",
                aspectRatio = 9f / 16f  // portrait video -> width/height
            ),
            // text post
            Post(
                id = "4",
                type = PostType.TEXT,
                text = "Small thread: Why RecyclerView performance matters."
            ),
            // landscape image
            Post(
                id = "5",
                type = PostType.IMAGE,
                imageRes = R.drawable.sample1,
                text = "Wide road ahead",
                aspectRatio = 16f / 9f
            )
        )
    }
}