package com.timife.githubapp.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.timife.githubapp.navigation.Route
import com.timife.githubapp.presentation.uistates.UserProfileData
import com.timife.githubapp.presentation.views.TextWithLeadingIcon
import com.timife.githubapp.presentation.views.UserItem

@Composable
fun ProfileSuccessView(
    data: UserProfileData,
    onNavigateToFollowers: (String) -> Unit, onNavigateToFollows: (String) -> Unit
) {
    val profile = data.profile
    val repos = data.repos
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = profile.avatarUrl, contentDescription = null, modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(50.dp)
                )
                Column {
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        text = profile.username,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        )
                    )
                }
            }
        }
        item {
            if (profile.bio.isNotEmpty()) {
                Text(text = profile.bio, style = MaterialTheme.typography.bodySmall)
            }
        }

        item {
            if (data.profile.location.isNotEmpty()) {
                TextWithLeadingIcon(
                    leadingIcon = Icons.Outlined.LocationOn,
                    text = profile.location
                )
            }
        }


        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TextWithLeadingIcon(
                    leadingIcon = Icons.Outlined.PersonOutline,
                    text = profile.followers.toString() + " followers",
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
                            onNavigateToFollowers(profile.username)
                        }
                        .padding(3.dp)
                )
                TextWithLeadingIcon(
                    leadingIcon = null,
                    text = profile.following.toString() + " following",
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
                            onNavigateToFollows(profile.username)
                        }
                        .padding(3.dp)
                )
            }
        }

        item {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithLeadingIcon(
                    leadingIcon = Icons.Rounded.Inventory2,
                    text = profile.publicRepos.toString() + " Repositories",
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
                        .padding(3.dp)
                )
            }
        }

        items(repos) { repo ->
            RepoItem(modifier = Modifier, repo = repo, profile = profile)
        }

    }
}