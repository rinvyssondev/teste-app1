package com.example.app_leal.screens.exercicies

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.app_leal.navigation.TrainingScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExercicieScreen(
    navController: NavController,
    viewModel: ExercicieViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val exercicios = viewModel.exercicio.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(TrainingScreens.ExercicieAddScreen.name)
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar Exercício")
            }
        }
    ) {
        Column {
            LazyColumn {
                items(exercicios.value.size) { exercicio ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { })
                            .padding(8.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically) {

                            Column(modifier = Modifier.padding(end = 60.dp)) {
                                Text(text = exercicios.value[exercicio].nome ?: "",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(170.dp)
                                )
                                Spacer(modifier = Modifier.padding(6.dp))
                                Text(text = exercicios.value[exercicio].observacoes ?: "",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.width(170.dp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            val imageUrl = exercicios.value[exercicio].imagens?.firstOrNull()
                            if (imageUrl != null) {
                                AsyncImage(
                                    imageUrl,
                                    contentDescription = exercicios.value[exercicio].nome ?: "",
                                    modifier = Modifier.padding(5.dp)
                                )
                            }


                        }
                        Row {
                            IconButton(onClick = {

                            }) {
                                Icon(Icons.Default.Update, contentDescription = "Atualizar Treino")
                            }

                            IconButton(onClick = {
                                    viewModel.deleteExercicio(exercicios.value[exercicio].nome ?: "")
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