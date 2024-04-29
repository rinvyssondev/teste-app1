package com.example.app_leal.screens.exercicies

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.app_leal.screens.home.Exercicie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class ExercicieViewModel : ViewModel() {
    private val db = Firebase.firestore

//    private val _exercicios = MutableLiveData<List<Exercicie>>()
    val exercicio = MutableStateFlow<List<Exercicie>>(emptyList())

    init {
        getAllExercicios()
    }

    private fun getAllExercicios() {
        db.collection("exercicios")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }

                val exerciciosList = ArrayList<Exercicie>()
                for (doc in value!!) {
                    val exercicio = doc.toObject(Exercicie::class.java)
                    exerciciosList.add(exercicio)
                }
                exercicio.value = exerciciosList
            }
    }

    fun addExercicio(exercicio: Exercicie) {
        db.collection("exercicios")
            .add(exercicio)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

//    fun deleteExercicio(nome: String) {
//        // Implemente a lógica de exclusão aqui
//        // Por exemplo, se você estiver usando o Firebase Firestore, você pode fazer algo assim:
//        val db = FirebaseFirestore.getInstance()
//        db.collection("exercicios")
//            .document(nome)
//            .delete()
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

//    }

    fun deleteExercicio(name: String?) {
        db.collection("exercicios")
            .whereEqualTo("nome", name)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("exercicios").document(document.id).delete()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}