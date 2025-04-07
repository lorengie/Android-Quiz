package com.example.tarea.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigationExample() {
    val navController = rememberNavController()
    val usuarios = remember { mutableStateListOf<Triple<String, String, String>>() }

    NavHost(navController, startDestination = "screen1") {
        composable("screen1") {
            screenA(navController, usuarios)
        }
        composable("screen2") {
            screenB(navController, usuarios)
        }
    }
}