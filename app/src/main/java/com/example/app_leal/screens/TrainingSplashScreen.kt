package com.example.app_leal.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_leal.components.TrainingLogo
import com.example.app_leal.navigation.TrainingScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun TrainingSplashScreen(
    navController: NavController
) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 1000,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(2000L)
//        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrBlank()){
//            navController.navigate(TrainingScreens.LoginScreen.name)
//        } else {
//            navController.navigate(TrainingScreens.TrainingHomeScreen.name)
//        }
        navController.navigate(TrainingScreens.LoginScreen.name)
    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            TrainingLogo()
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = "Training is health, training is life",
                color = Color.LightGray
            )
        }
    }
}