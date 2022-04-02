package com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.login

import android.app.Activity
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.common.StartActivityForResult
import com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.common.signIn

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val signedInState by loginViewModel.signedState
    val messageBarState by loginViewModel.messageBarState

    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    loginViewModel.saveSignedInState(true)
                }
            )
        }
    )
    val activity = LocalContext.current as Activity

    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            Log.d("LoginScreen", tokenId)
        },
        onDialogDismissed = { loginViewModel.saveSignedInState(signedIn = false) }
    ) { activityLauncher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
            )
        }
    }
}