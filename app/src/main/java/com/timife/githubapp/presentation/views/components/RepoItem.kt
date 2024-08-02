package com.timife.githubapp.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.presentation.ui.theme.GithubAppTheme
import com.timife.githubapp.presentation.views.TextWithLeadingIcon

@Composable
fun RepoItem(
    modifier: Modifier,
    repo: Repo,
    profile: UserProfile
) {

    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(5.dp)
            )
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AsyncImage(
                model = profile.avatarUrl, contentDescription = null, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(15.dp)
            )
            Text(
                text = profile.username,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Text(
            text = repo.name,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = repo.description,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextWithLeadingIcon(leadingIcon = Icons.Filled.Star, text = repo.stars.toString())
            TextWithLeadingIcon(leadingIcon = Icons.Filled.Circle, text = repo.language)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    GithubAppTheme {
        Surface(onClick = { /*TODO*/ }, modifier = Modifier.padding(50.dp)) {
            RepoItem(
                Modifier.padding(10.dp),
                Repo(1, "", true, "", false, "", "", 2, "public", 10),
                UserProfile(
                    "",
                    10,
                    "", 10,
                    "", 10, "",
                    "", "", 10, "",
                    "",
                    ""
                )
            )
        }
    }
}