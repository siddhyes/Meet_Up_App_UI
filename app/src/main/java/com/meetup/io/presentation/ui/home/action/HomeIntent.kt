package com.meetup.io.presentation.ui.home.action



sealed class HomeIntent {
    object LoadActivities : HomeIntent()
    data class SelectTab(val tab: ActivityTab) : HomeIntent()
    data class SelectCategory(val categoryId: String) : HomeIntent()
    data class JoinActivity(val activityId: String) : HomeIntent()
    data class LikeActivity(val activityId: String) : HomeIntent()
}
enum class ActivityTab {
    ALL, PLANNED_ACTIVITY, LIVE_ACTIVITY
}