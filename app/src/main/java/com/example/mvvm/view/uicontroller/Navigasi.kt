package com.example.mvvm.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    FormSiswa,
    Tampilan
}

@Composable
fun SiswaApp(
    modifier: Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),

){
    Scaffold { isiRuang->

        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.FormSiswa.name,

            modifier = Modifier.padding(isiRuang)){
            composable(route = Navigasi.FormSiswa.name){
                FormSiswa (
                    //pilihanJK = JenisK.map { id -> konteks.resources.getString}
                    OnSubmitBtnClick = {
                        navController.navigate(Navigasi.Tampilan.name)
                    }
                )
            }
            composable(route = Navigasi.Tampilan.name){
                TampilanSiswa(
                    onBackClick = {
                        cancelAndBackToFormulirku(navController)
                    }
                )
            }
        }
    }
}


private fun cancelAndBackToFormulirku(
    navController:NavHostController
){
    navController.popBackStack(Navigasi.Formulirku.name,
        inclusive = false)
}
