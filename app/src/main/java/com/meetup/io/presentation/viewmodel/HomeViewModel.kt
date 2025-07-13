package com.meetup.io.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meetup.io.domain.model.Activity
import com.meetup.io.domain.model.Category
import com.meetup.io.domain.model.User
import com.meetup.io.presentation.ui.home.action.ActivityCardAction
import com.meetup.io.presentation.ui.home.action.ActivityTab
import com.meetup.io.presentation.ui.home.action.HomeIntent
import com.meetup.io.presentation.ui.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        handleIntent(HomeIntent.LoadActivities)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadActivities -> loadActivities()
            is HomeIntent.SelectTab -> selectTab(intent.tab)
            is HomeIntent.SelectCategory -> selectCategory(intent.categoryId)
            is HomeIntent.JoinActivity -> joinActivity(intent.activityId)
            is HomeIntent.LikeActivity -> likeActivity(intent.activityId)
        }
    }

    fun handleActivityCardAction(action: ActivityCardAction) {
        when (action) {
            is ActivityCardAction.JoinActivity -> joinActivity(action.activityId)
            is ActivityCardAction.LeaveActivity -> leaveActivity(action.activityId)
            is ActivityCardAction.LikeActivity -> likeActivity(action.activityId)
            is ActivityCardAction.UnlikeActivity -> unlikeActivity(action.activityId)
            is ActivityCardAction.CommentOnActivity -> openComments(action.activityId)
            is ActivityCardAction.ShareActivity -> shareActivity(action.activityId)
            is ActivityCardAction.ViewProfile -> viewProfile(action.userId)
            is ActivityCardAction.ViewLocation -> viewLocation(action.location)
            is ActivityCardAction.ViewActivityDetails -> viewActivityDetails(action.activityId)
            is ActivityCardAction.ReportActivity -> reportActivity(action.activityId)
            is ActivityCardAction.BookmarkActivity -> bookmarkActivity(action.activityId)
            is ActivityCardAction.ShowMoreOptions -> showMoreOptions(action.activityId)
        }
    }

    private fun joinActivity(activityId: String) {
        val updatedActivities = _state.value.activities.map { activity ->
            if (activity.id == activityId) {
                activity.copy(
                    isJoined = true,
                    participantCount = activity.participantCount + 1
                )
            } else activity
        }
        _state.value = _state.value.copy(activities = updatedActivities)
    }

    private fun leaveActivity(activityId: String) {
        val updatedActivities = _state.value.activities.map { activity ->
            if (activity.id == activityId) {
                activity.copy(
                    isJoined = false,
                    participantCount = activity.participantCount - 1
                )
            } else activity
        }
        _state.value = _state.value.copy(activities = updatedActivities)
    }

    private fun likeActivity(activityId: String) {
        val updatedActivities = _state.value.activities.map { activity ->
            if (activity.id == activityId) {
                activity.copy(
                    isLiked = true,
                    likes = activity.likes + 1
                )
            } else activity
        }
        _state.value = _state.value.copy(activities = updatedActivities)
    }

    private fun unlikeActivity(activityId: String) {
        val updatedActivities = _state.value.activities.map { activity ->
            if (activity.id == activityId) {
                activity.copy(
                    isLiked = false,
                    likes = maxOf(0, activity.likes - 1)
                )
            } else activity
        }
        _state.value = _state.value.copy(activities = updatedActivities)
    }

    private fun bookmarkActivity(activityId: String) {
        val updatedActivities = _state.value.activities.map { activity ->
            if (activity.id == activityId) {
                activity.copy(isBookmarked = !activity.isBookmarked)
            } else activity
        }
        _state.value = _state.value.copy(activities = updatedActivities)
    }

    private fun openComments(activityId: String) {


    }

    private fun shareActivity(activityId: String) {
        // Implement share functionality
    }

    private fun viewProfile(userId: String) {
        // Navigate to user profile
    }

    private fun viewLocation(location: String) {
        // Open maps with location
    }

    private fun viewActivityDetails(activityId: String) {
        // Navigate to activity details
    }

    private fun reportActivity(activityId: String) {
        // Show report dialog
    }

    private fun showMoreOptions(activityId: String) {
        // Show bottom sheet with more options
    }
    private fun loadActivities() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val activities = getSampleActivities()
            val categories = getSampleCategories()

            _state.value = _state.value.copy(
                activities = activities,
                categories = categories,
                isLoading = false
            )
        }
    }

    private fun selectTab(tab: ActivityTab) {
        _state.value = _state.value.copy(selectedTab = tab)
    }

    private fun selectCategory(categoryId: String) {
        val updatedCategories = _state.value.categories.map { category ->
            category.copy(isSelected = category.id == categoryId)
        }
        _state.value = _state.value.copy(categories = updatedCategories)
    }





    private fun getSampleActivities(): List<Activity> {
        return listOf(
            Activity(
                id = "1",
                user = User("1", "Jemmy Ray", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=100&h=100&fit=crop&crop=face"),
                title = "Chilling at Summer House Café",
                description = "Lorem Ipsum has been the industry's standard..",
                location = "Summer House Café",
                dateTime = "Saturday, October 21 at 7:00 PM",
                timeAgo = "2 hour ago",
                participantCount = 16,
                isJoined = true
            ),
            Activity(
                id = "2",
                user = User("2", "Emma Lily", "https://images.unsplash.com/photo-1600600423621-70c9f4416ae9?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGdpcmxzfGVufDB8fDB8fHww"),
                title = "Amazing Music Experience",
                description = "Today, I experienced the most amazing music and The air feels amazing... more",
                location = "Summer House Café",
                dateTime = "Today",
                timeAgo = "9 min ago",
                participantCount = 12,
                imageUrl = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=400&h=600&fit=crop",
                isLive = true,
                likes = 7,
                comments = 2,
                shares = 12
            ),
            Activity(
                id = "3",
                user = User("3", "Mera Joseph", "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=100&h=100&fit=crop&crop=face"),
                title = "Chilling at Summer House Café",
                description = "Lorem Ipsum has been the industry's standard..",
                location = "Summer House Café",
                dateTime = "Jan 14 2024 | 10:30",
                timeAgo = "1 day ago",
                participantCount = 8,
                likes = 15,
                comments = 3,
                shares = 5
            ),

                    Activity(
                    id = "3",
            user = User("2", "SIMA Lily", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            title = "Spotify Music Experience",
            description = "Today, I experienced the most amazing music and The air feels amazing... more",
            location = "Green House Café",
            dateTime = "Today",
            timeAgo = "10 min ago",
            participantCount = 17,
            imageUrl = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=400&h=600&fit=crop",
            isLive = true,
            likes = 27,
            comments = 32,
            shares = 123
        ),
        )
    }

    private fun getSampleCategories(): List<Category> {
        return listOf(
            Category("all", "All", "grid", true),
            Category("basketball", "Basketball", "sports_basketball", false),
            Category("bmx", "BMX", "directions_bike", false),
            Category("debate", "Debate", "forum", false),
            Category("critical", "Critical..", "psychology", false),
            Category("community", "Community", "groups", false),
            Category("blood", "Blood Dona..", "favorite", false)
        )
    }
}
