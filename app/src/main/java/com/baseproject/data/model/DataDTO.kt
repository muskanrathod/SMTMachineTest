package com.baseproject.data.model

data class DataDTO(
    val posts: ArrayList<Post>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)

data class Post(
    val id: Long,
    val title: String,
    val body: String,
    val tags: ArrayList<String>,
    val reactions: Reactions,
    val views: Long,
    val userId: Long,
)

data class Reactions(
    val likes: Long,
    val dislikes: Long,
)