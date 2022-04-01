package com.metehanbolat.fullstackandroidprojectcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.fullstackandroidprojectcompose.ui.theme.FullStackAndroidProjectComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullStackAndroidProjectComposeTheme {

            }
        }
    }
}