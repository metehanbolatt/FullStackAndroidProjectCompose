package com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.metehanbolat.fullstackandroidprojectcompose.domain.model.MessageBarState

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(signedInState = true, messageBarState = MessageBarState()) {

            }
        }
    )
}