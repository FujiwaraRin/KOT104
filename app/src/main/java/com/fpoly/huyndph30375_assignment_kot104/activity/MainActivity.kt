package com.fpoly.huyndph30375_assignment_kot104.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fpoly.huyndph30375_assignment_kot104.bottom_nav.ScreenNav
import com.fpoly.huyndph30375_assignment_kot104.ui.theme.huyndph30375_assignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            huyndph30375_assignment {
               ScreenNav()
            }
        }
    }
}