package com.example.app_leal.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_leal.navigation.TrainingScreens
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingAddScreen(
    navController: NavController,
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }

    val viewModel: TreinoViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ADICIONAR TREINO", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.surfaceTint,
                ),)
        },
    ) {

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Data") }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            try {
            val date = format.parse(data)
            val treino = Treino(
                nome = nome, descricao = descricao,
                data = Timestamp(date))
                    viewModel.addTreino(treino)
                    navController.navigate(TrainingScreens.TrainingHomeScreen.name)
            } catch (e: Exception) {
                Toast.makeText(context, "Digite nesse formato: dd/MM/yyyy", Toast.LENGTH_SHORT).show()
            }
//            viewModel.addTreino(treino)

        }) {
            Text("Adicionar Treino")
        }
    }
    }
    }
}

