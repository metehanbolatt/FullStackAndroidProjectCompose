package com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.login

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.metehanbolat.fullstackandroidprojectcompose.R
import com.metehanbolat.fullstackandroidprojectcompose.ui.theme.topAppBarBackgroundColor
import com.metehanbolat.fullstackandroidprojectcompose.ui.theme.topAppBarContentColor

@Composable
fun LoginTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.sign_in),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}