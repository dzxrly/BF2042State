package com.eggtargaryen.bf2042state.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.model.UpdateInfoViewModel
import com.eggtargaryen.bf2042state.page.LoginPage
import com.eggtargaryen.bf2042state.page.StatePage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


object Destinations {
    const val LOGIN = "login"
    const val STATE = "state"
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    startDestination: String = Destinations.LOGIN,
    durationMillis: Int = 350,
    easing: Easing = FastOutSlowInEasing
) {
    val navController = rememberAnimatedNavController()
    // PlayerInfoViewModel is a ViewModel that stores the player info
    val playerInfoViewModel = viewModel<PlayerInfoViewModel>()
    val updateInfoViewModel = viewModel<UpdateInfoViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            when (initialState.destination.route) {
                Destinations.LOGIN -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = durationMillis, easing = easing)
                )

                else -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = durationMillis, easing = easing)
                )
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                Destinations.LOGIN -> slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = durationMillis, easing = easing)
                )

                else -> slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = durationMillis, easing = easing)
                )
            }
        }
    ) {
        composable(Destinations.LOGIN) {
            LoginPage(
                onNavToState = {
                    navController.navigate(Destinations.STATE)
                },
                playerInfoViewModel = playerInfoViewModel,
                updateInfoViewModel = updateInfoViewModel
            )
        }
        composable(Destinations.STATE) {
            StatePage(
                onNavToLogin = {
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.LOGIN) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                playerInfoViewModel = playerInfoViewModel
            )
        }
    }
}