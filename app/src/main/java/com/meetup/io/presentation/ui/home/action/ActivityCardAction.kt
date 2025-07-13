package com.meetup.io.presentation.ui.home.action

sealed interface ActivityCardAction {
    data class JoinActivity(val activityId: String) : ActivityCardAction
    data class LeaveActivity(val activityId: String) : ActivityCardAction
    data class LikeActivity(val activityId: String) : ActivityCardAction
    data class UnlikeActivity(val activityId: String) : ActivityCardAction
    data class CommentOnActivity(val activityId: String) : ActivityCardAction
    data class ShareActivity(val activityId: String) : ActivityCardAction
    data class ViewProfile(val userId: String) : ActivityCardAction
    data class ViewLocation(val location: String) : ActivityCardAction
    data class ViewActivityDetails(val activityId: String) : ActivityCardAction
    data class ReportActivity(val activityId: String) : ActivityCardAction
    data class BookmarkActivity(val activityId: String) : ActivityCardAction
    data class ShowMoreOptions(val activityId: String) : ActivityCardAction
}
