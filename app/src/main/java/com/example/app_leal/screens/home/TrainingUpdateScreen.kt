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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_leal.navigation.TrainingScreens
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun TrainingUpdateScreen(
    navController: NavController,
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }

    val viewModel: TreinoViewModel = viewModel()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()
        .padding(10.dp)){
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Atualizar Treino", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") }
            )
            TextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") }
            )
            TextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                try {
                    val date = format.parse(data)
                    val treino = Treino(
                        nome = nome, descricao = descricao,
                        data = Timestamp(date))
                    viewModel.updateTraining(treino.id, treino.nome, treino.descricao, treino.data)
                    navController.navigate(TrainingScreens.TrainingHomeScreen.name)
                } catch (e: Exception) {
                    Toast.makeText(context, "Digite nesse formato: dd/MM/yyyy", Toast.LENGTH_SHORT).show()
                }
//            viewModel.addTreino(treino)

            }) {
                Text("Atualizar Treino")
            }
        }}
}

