package com.example.app_leal.screens.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class TreinoViewModel : ViewModel() {
    private val db = Firebase.firestore

    val treinos = MutableStateFlow<List<Treino>>(emptyList())

    init {
        fetchTreinos()
    }

    private fun fetchTreinos() {
        db.collection("treinos")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }

                val treinosList = mutableListOf<Treino>()
                for (doc in value!!) {
                    val treino = doc.toObject(Treino::class.java)
                    treinosList.add(treino)
                }
                treinos.value = treinosList
            }
    }

    fun addTreino(treino: Treino) {

        db.collection("treinos")
            .add(treino)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                println("${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun deleteTraining(name: String?, Description: String?) {
        db.collection("treinos")
            .whereEqualTo("nome", name)
            .whereEqualTo("descricao", Description)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("treinos").document(document.id).delete()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun getTreinoById(id: String) {
        db.collection("treinos").document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }


    fun updateTraining(id: String?, nome: String?, descricao: String?, data: Timestamp?) {
        val updates = hashMapOf<String, Any?>(
            "nome" to nome,
            "descricao" to descricao,
            "data" to data
        )
        println(id.toString())

        db.collection("treinos").document(id.toString())
            .update(updates)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }


    // Implemente as funções de atualizar e excluir de maneira semelhante
}