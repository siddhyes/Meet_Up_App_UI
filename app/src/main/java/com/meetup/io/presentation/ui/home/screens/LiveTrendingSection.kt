package com.meetup.io.presentation.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meetup.io.presentation.ui.home.action.ActivityTab
import com.meetup.io.presentation.ui.theme.PrimaryPurple


@Composable
fun LiveTrendingSection(
    selectedTab: ActivityTab,
    onTabSelected: (ActivityTab) -> Unit
) {
    Column(
        modifier = Modifier.background(Color(0xFFEEF8FF)).padding(16.dp)
    ) {
        Text(
            text = "Live & Trending Near You",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TabItem(
                text = "All",
                isSelected = selectedTab == ActivityTab.ALL,
                onClick = { onTabSelected(ActivityTab.ALL) },
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "Planned Activity",
                isSelected = selectedTab == ActivityTab.PLANNED_ACTIVITY,
                onClick = { onTabSelected(ActivityTab.PLANNED_ACTIVITY) },
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "Live Activity",
                isSelected = selectedTab == ActivityTab.LIVE_ACTIVITY,
                onClick = { onTabSelected(ActivityTab.LIVE_ACTIVITY) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) PrimaryPurple else Color.Transparent
            )
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isSelected && text == "Live Activity") {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Green)
                )
            }

            Text(
                text = text,
                fontSize = 12.sp,
                color = if (isSelected) Color.White else Color.Gray,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )
        }
    }
}
