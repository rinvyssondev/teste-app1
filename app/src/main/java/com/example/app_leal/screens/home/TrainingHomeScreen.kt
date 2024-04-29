package com.example.app_leal.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_leal.navigation.TrainingScreens
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingHomeScreen(
    viewModel: TreinoViewModel = viewModel(),
    navController: NavController
) {
    val treinos = viewModel.treinos.collectAsState(initial = emptyList())
    val context= LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(TrainingScreens.TrainingAddScreen.name)  }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Treino")
            }
        },
        topBar = {
            TopAppBar(title = { Text("TREINOS", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.surfaceTint,
                ),)
        },
    ) {
        Column {
            Spacer(modifier = Modifier.height(70.dp))
            LazyColumn {
                items(treinos.value.size) { treino ->
//                Text(text = treinos.value[treino].nome ?: "")
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { navController.navigate(TrainingScreens.ExercicieScreen.name) })
                            .padding(8.dp)
                    ) {
//                    Text(text = treinos.value[treino].nome ?: "")
                        Column(modifier = Modifier.padding(16.dp)) {

                            val treinoData = treinos.value[treino].data?.toDate()
                            if (treinoData == null) {
                                return@Card
                            }
                            val formated = SimpleDateFormat("dd/MM/yyyy").format(treinoData)
                            val dateFomated = formated.format(treinoData)
                            Text(text = treinos.value[treino].nome ?: "", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(text = treinos.value[treino].descricao ?: "", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Normal)
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(text = dateFomated ?: "", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Normal)
                        }
                        Row {
                            IconButton(onClick = {
                                navController.navigate(TrainingScreens.TrainingUpdateScreen.name)
                            }) {
                                Icon(Icons.Default.Update, contentDescription = "Atualizar Treino")
                            }

                            IconButton(onClick = {
                                viewModel.deleteTraining(treinos.value[treino].nome, treinos.value[treino].descricao)
                                Toast.makeText(context, "Documento deletado com sucesso", Toast.LENGTH_SHORT).show()

                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Deletar Treino")
                            }
                        }
                    }
                }
            }

        }

    }
}