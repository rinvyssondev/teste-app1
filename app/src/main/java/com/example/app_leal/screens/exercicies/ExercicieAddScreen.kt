package com.example.app_leal.screens.exercicies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_leal.screens.home.Exercicie
import com.example.app_leal.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExercicieAddScreen(
    navController: NavController
) {

    val viewModel = ExercicieViewModel()

    var nome by remember { mutableStateOf("") }
    var imagemUrl by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }
    var imagens by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ADICIONAR EXERCÍCIO", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.surfaceTint,
                ),)
        },
    ) {

    Box(modifier = Modifier
        .padding(start = 10.dp, end = 40.dp, top = 50.dp)
        .fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do Exercício") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = imagemUrl,
                onValueChange = { imagemUrl = it },
                label = { Text("URL da Imagem") }
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(onClick = { imagens = imagens + listOf(imagemUrl) }) {
                Text("Adicionar Imagem")
            }
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = observacoes,
                onValueChange = { observacoes = it },
                label = { Text("Observações") }
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(onClick = {
                val exercicio = Exercicie(nome, imagens, observacoes)
                viewModel.addExercicio(exercicio)
                navController.popBackStack()
            }) {
                Text("Criar Exercício")
            }
        }
    }
    }
}

//https://www.basefitness.com.br/wp-content/uploads/2020/02/m%C3%BAsculos-envolvidos-rosca-direta.jpg