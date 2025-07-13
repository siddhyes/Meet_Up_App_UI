package com.meetup.io.presentation.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meetup.io.domain.model.Category
import com.meetup.io.presentation.ui.theme.PrimaryPurple

@Composable
fun CategoriesSection(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit={}
) {
    Column(

        modifier = Modifier.background(Color(0xFFEEF8FF)).padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Passion & Pursuits------------------------------------------",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 1.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategoryClick(category.id) }
                )
            }
        }
    }
}


@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (category.isSelected) PrimaryPurple else Color.Gray.copy(alpha = 0.1f)
                ),
            contentAlignment = Alignment.Center
        ) {


            Icon(
                imageVector = getCategoryIcon(category.icon),
                contentDescription = category.name,
                tint = if (category.isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            textDecoration = if (category.isSelected) TextDecoration.Underline else TextDecoration.None,
            text = category.name,
            fontSize = 12.sp,
            color = if (category.isSelected) PrimaryPurple else Color.Gray,
            fontWeight = if (category.isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
fun getCategoryIcon(iconName: String): ImageVector {
    return when (iconName) {
        "grid" -> Icons.Default.Apps
        "sports_basketball" -> Icons.Default.SportsBasketball
        "directions_bike" -> Icons.Default.DirectionsBike
        "forum" -> Icons.Default.Forum
        "psychology" -> Icons.Default.Psychology
        "groups" -> Icons.Default.Groups
        "favorite" -> Icons.Default.Favorite
        else -> Icons.Default.Apps
    }
}