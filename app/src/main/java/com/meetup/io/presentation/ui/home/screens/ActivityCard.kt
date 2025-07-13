package com.meetup.io.presentation.ui.home.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.meetup.io.domain.model.Activity
import com.meetup.io.domain.model.User
import com.meetup.io.presentation.ui.home.action.ActivityCardAction
import com.meetup.io.presentation.ui.theme.InterestButtonColor
import com.meetup.io.presentation.ui.theme.PrimaryPurple

@Composable
fun ActivityCard(
    activity: Activity,
    onAction: (ActivityCardAction) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onAction(ActivityCardAction.ViewActivityDetails(activity.id))
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        if (activity.imageUrl != null) {

            FullImageActivityCard(
                activity = activity,
               onAction = onAction
            )
        } else {

            StandardActivityCard(
                activity = activity,
                onAction = onAction
            )
        }
    }
}


@Composable
private fun FullImageActivityCard(
    activity: Activity,
    onAction: (ActivityCardAction) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {

        AsyncImage(
            model = activity.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAction(ActivityCardAction.ViewProfile(activity.user.id))
                }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = activity.user.profilePicUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)

                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = activity.user.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Text(
                        text = activity.timeAgo,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }



        }

        // Content at bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Location info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = activity.location,
                    fontSize = 14.sp,
                    color = Color.White
                )

                // Date time for full image cards
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = activity.dateTime,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            // Description
            Text(
                text = activity.description,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Engagement buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    EngagementButton(
                        icon = Icons.Default.FavoriteBorder,
                        count = activity.likes,
                        onClick = {onAction(ActivityCardAction.LikeActivity(activity.id))},
                        tint = Color.White
                    )

                    EngagementButton(
                        icon = Icons.Default.ChatBubbleOutline,
                        count = activity.comments,
                        onClick = { onAction(ActivityCardAction.CommentOnActivity(activity.id)) },
                        tint = Color.White
                    )

                    EngagementButton(
                        icon = Icons.Default.Share,
                        count = activity.shares,
                        onClick = { onAction(ActivityCardAction.ShowMoreOptions(activity.id)) },
                        tint = Color.White
                    )
                }


                IconButton(onClick = {onAction(ActivityCardAction.ShowMoreOptions(activity.id))}) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun StandardActivityCard(
    activity: Activity,
    onAction: (ActivityCardAction) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Header with user info and action button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = activity.user.profilePicUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = activity.user.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = activity.timeAgo,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Interest/Join Button
            Button(
                onClick = {onAction(ActivityCardAction.JoinActivity(activity.id))},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (activity.isJoined) Color.Gray else InterestButtonColor
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = if (activity.isJoined) Icons.Default.Check else Icons.Default.PanTool,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (activity.isJoined) "Joined" else "Interest",
                    fontSize = 12.sp,
                    color = Color.White
                )
                if (!activity.isJoined) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Requested",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Activity title with location icon
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = PrimaryPurple,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = activity.title,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }

        // Description
        Text(
            text = activity.description,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Event details row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Date and time
            Icon(
                imageVector = Icons.Default.Event,
                contentDescription = null,
                tint = PrimaryPurple,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = activity.dateTime,
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Participants count
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = null,
                    tint = PrimaryPurple,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${activity.participantCount}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (activity.isJoined) "Joined" else "People",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Show engagement buttons for standard cards if they have interactions
        if (activity.likes > 0 || activity.comments > 0 || activity.shares > 0) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (activity.likes > 0) {
                    EngagementButton(
                        icon = Icons.Default.FavoriteBorder,
                        count = activity.likes,
                        onClick = {onAction(ActivityCardAction.LikeActivity(activity.id))},
                        tint = Color.Gray
                    )
                }

                if (activity.comments > 0) {
                    EngagementButton(
                        icon = Icons.Default.ChatBubbleOutline,
                        count = activity.comments,
                        onClick = {},
                        tint = Color.Gray
                    )
                }

                if (activity.shares > 0) {
                    EngagementButton(
                        icon = Icons.Default.Share,
                        count = activity.shares,
                        onClick = {},
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun EngagementButton(
    icon: ImageVector,
    count: Int,
    onClick: () -> Unit,
    tint: Color = Color.White
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
        if (count > 0) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = count.toString(),
                fontSize = 14.sp,
                color = tint
            )
        }
    }
}