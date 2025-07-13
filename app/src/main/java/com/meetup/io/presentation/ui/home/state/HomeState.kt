package com.meetup.io.presentation.ui.home.state

import com.meetup.io.domain.model.Activity
import com.meetup.io.domain.model.Category
import com.meetup.io.presentation.ui.home.action.ActivityTab

data class HomeState(
    val activities: List<Activity> = emptyList(),
    val categories: List<Category> = emptyList(),
    val selectedTab: ActivityTab = ActivityTab.ALL,
    val isLoading: Boolean = false,
    val error: String? = null,
    val locationString: String = "Prestige Lakeside Habitat Habitat.....",
)