package com.timife.githubapp.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.presentation.views.TextWithLeadingIcon

@Composable
fun ProfileSuccessView(
    userProfile: UserProfile,
    onNavigateToFollowers: (String) -> Unit, onNavigateToFollows: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = userProfile.avatarUrl, contentDescription = null, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(50.dp)
            )
            Column {
                Text(
                    text = userProfile.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = userProfile.username,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )
                )
            }
        }
        if (userProfile.bio.isNotEmpty()) {
            Text(text = userProfile.bio, style = MaterialTheme.typography.bodySmall)
        }

        if (userProfile.location.isNotEmpty()) {
            TextWithLeadingIcon(
                leadingIcon = Icons.Outlined.LocationOn,
                text = userProfile.location
            )
        }


        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            TextWithLeadingIcon(
                leadingIcon = Icons.Outlined.PersonOutline,
                text = userProfile.followers.toString() + " followers",
                modifier = Modifier
                    .height(25.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Color.White)
                    .clickable {
                        onNavigateToFollowers(userProfile.username)
                    }
                    .padding(3.dp)
            )
            TextWithLeadingIcon(
                leadingIcon = null,
                text = userProfile.following.toString() + " following",
                Modifier
                    .height(25.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Color.White)
                    .clickable {
                        onNavigateToFollows(userProfile.username)
                    }
                    .padding(3.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithLeadingIcon(leadingIcon = Icons.Rounded.Inventory2,
                text = userProfile.publicRepos.toString() + " Repositories",
                modifier = Modifier
                    .height(25.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Color.White)
                    .clickable {
//                        navController.navigate(Route.ReposScreen.route + "/${userProfile.username}")
                    }
                    .padding(3.dp)
            )
        }
    }
}