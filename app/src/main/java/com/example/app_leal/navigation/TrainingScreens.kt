package com.example.app_leal.navigation

enum class TrainingScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    TrainingHomeScreen,
    TrainingAddScreen,
    TrainingUpdateScreen,
    ExercicieScreen,
    ExercicieAddScreen,
    DetailScreen;

    companion object {
        fun fromRoute(route: String?): TrainingScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                CreateAccountScreen.name -> CreateAccountScreen
                TrainingHomeScreen.name -> TrainingHomeScreen
                TrainingAddScreen.name -> TrainingAddScreen
                TrainingUpdateScreen.name -> TrainingUpdateScreen
                ExercicieScreen.name -> ExercicieScreen
                ExercicieAddScreen.name -> ExercicieAddScreen
                DetailScreen.name -> DetailScreen
                null -> TrainingHomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
