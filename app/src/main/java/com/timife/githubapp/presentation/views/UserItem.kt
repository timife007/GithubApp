package com.timife.githubapp.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.timife.githubapp.presentation.uistates.UserResult

@Composable
fun UserItem(
    modifier: Modifier,
    user: UserResult
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = if (user.description == null) Alignment.CenterVertically else Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AsyncImage(
            model = user.avatar,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            if (user.name != null) {
                Text(
                    text = user.name,
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
            Text(
                text = user.username,
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            )
            if (user.description != null) {
                Text(
                    text = user.description,
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )
            }
        }
    }
}