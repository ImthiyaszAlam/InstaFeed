package com.imthiyas.instafeed.model


enum class PostType {
    IMAGE,
    VIDEO,
    TEXT
}

data class Post(
    val id: String,
    val type: PostType,
    val imageRes: Int? = null,
    val imageUrl: String? = null,
    val videoRes: Int? = null,
    val videoUrl: String? = null,
    val text: String? = null,
    val aspectRatio: Float? = null
)
