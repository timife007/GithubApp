package com.timife.githubapp.presentation.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.githubapp.presentation.ui.theme.GithubAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    modifier: Modifier,
    title: String,
    subtitle: String,
    onNavigateBack: () -> Unit,
    isHome: Boolean = false
) {

    TopAppBar(title = {
        Column(
            verticalArrangement = if (isHome) Arrangement.Center else Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            )
            if (!isHome) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.DarkGray)
                )
            }
        }
    }, navigationIcon = {
        if (!isHome) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onNavigateBack()
                })
        }
    }, modifier = modifier)

}

@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    GithubAppTheme {
        Surface(Modifier.padding(20.dp)) {
            CustomAppBar(Modifier.padding(10.dp), "timife", "following", onNavigateBack = {})
        }
    }
}