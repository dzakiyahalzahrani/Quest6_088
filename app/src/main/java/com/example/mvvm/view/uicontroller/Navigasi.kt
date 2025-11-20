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
import androidx.compose.ui.platform.LocalContext
import com.example.mvvm.model.DataJK.JenisK
import com.example.mvvm.view.FormSiswa
import com.example.mvvm.view.TampilanSiswa
import com.example.mvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulir,
    Tampilan
}

@Composable
fun SiswaApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),

){
    Scaffold (modifier = modifier) { isiRuang->

        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(isiRuang)){
            composable(route = Navigasi.Formulir.name){
                val konteks = LocalContext.current
                FormSiswa (
                    pilihanK = JenisK.map { id -> konteks.resources.getString(id)},
                    OnSubmitBtnClick = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Tampilan.name)
                    }
                )
            }
            composable(route = Navigasi.Tampilan.name){
                TampilanSiswa(
                    statusUiSiswa = uiState.value,
                    onBackClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}


private fun cancelAndBackToFormulir(
    navController:NavHostController
){
    navController.popBackStack(Navigasi.Formulir.name,
        inclusive = false)
}
