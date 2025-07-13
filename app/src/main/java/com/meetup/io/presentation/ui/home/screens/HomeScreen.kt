package com.meetup.io.presentation.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.meetup.io.presentation.ui.home.action.HomeIntent
import com.meetup.io.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMinimalChanges(
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        TopAppBar(
            location = state.locationString,
            onSearchClick = { /* Handle search */ },
            onNotificationClick = { /* Handle notification */ }
        )

        CategoriesSection(
            categories = state.categories,
            onCategoryClick = { categoryId ->
                viewModel.handleIntent(HomeIntent.SelectCategory(categoryId))
            }
        )

        Spacer(modifier = Modifier.height(16.dp).background(color = Color.White))

        LiveTrendingSection(
            selectedTab = state.selectedTab,
            onTabSelected = { tab ->
                viewModel.handleIntent(HomeIntent.SelectTab(tab))
            }
        )


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.activities) { activity ->
                ActivityCard(
                    activity = activity,
                    onAction = { action ->
                        viewModel.handleActivityCardAction(action)
                    },
                )
            }
        }


        BottomNavigationBar()
    }
}