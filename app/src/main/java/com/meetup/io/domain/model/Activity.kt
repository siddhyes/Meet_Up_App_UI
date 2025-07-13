package com.meetup.io.domain.model

data class Activity(
    val id: String,
    val user: User,
    val title: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val timeAgo: String,
    val participantCount: Int,
    val imageUrl: String? = null,
    val isJoined: Boolean = false,
    val isLive: Boolean = false,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false
)
data class User(
    val id: String,
    val name: String,
    val profilePicUrl: String
)
data class Category(
    val id: String,
    val name: String,
    val icon: String,
    val isSelected: Boolean = false
)


