package com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.login

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.metehanbolat.fullstackandroidprojectcompose.domain.model.ApiRequest
import com.metehanbolat.fullstackandroidprojectcompose.domain.model.ApiResponse
import com.metehanbolat.fullstackandroidprojectcompose.navigation.Screen
import com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.common.StartActivityForResult
import com.metehanbolat.fullstackandroidprojectcompose.presentation.screen.common.signIn
import com.metehanbolat.fullstackandroidprojectcompose.util.RequestState

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val signedInState by loginViewModel.signedState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

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
            loginViewModel.verifyTokenBackend(
                request = ApiRequest(tokenId = tokenId)
            )
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

    LaunchedEffect(key1 = apiResponse) {
        when(apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (response) {
                    navigateToProfileScreen(navController = navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }
}

private fun navigateToProfileScreen(navController: NavHostController) {
    navController.navigate(route = Screen.Profile.route) {
        popUpTo(route = Screen.Login.route) {
            inclusive = true
        }
    }
}