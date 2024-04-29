package com.example.app_leal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_leal.screens.TrainingSplashScreen
import com.example.app_leal.screens.exercicies.ExercicieAddScreen
import com.example.app_leal.screens.exercicies.ExercicieScreen
import com.example.app_leal.screens.home.TrainingAddScreen
import com.example.app_leal.screens.home.TrainingHomeScreen
import com.example.app_leal.screens.home.TrainingUpdateScreen
import com.example.app_leal.screens.login.TrainingLoginScreen

@Composable
fun TrainingNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TrainingScreens.SplashScreen.name) {
        composable(TrainingScreens.SplashScreen.name) {
            TrainingSplashScreen(navController = navController)
        }

        composable(TrainingScreens.LoginScreen.name) {
            TrainingLoginScreen(navController = navController)
        }

        composable(TrainingScreens.TrainingHomeScreen.name) {
            TrainingHomeScreen(navController = navController)
        }
        composable(TrainingScreens.TrainingAddScreen.name) {
            TrainingAddScreen(navController = navController)
        }
        composable(TrainingScreens.TrainingUpdateScreen.name) {
            TrainingUpdateScreen(navController = navController)
        }
        composable(TrainingScreens.ExercicieScreen.name) {
            ExercicieScreen(navController = navController)
        }
        composable(TrainingScreens.ExercicieAddScreen.name) {
            ExercicieAddScreen(navController = navController)
        }
    }
}
