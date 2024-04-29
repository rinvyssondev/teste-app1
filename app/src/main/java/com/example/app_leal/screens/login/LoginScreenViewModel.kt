package com.example.app_leal.screens.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_leal.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginScreenViewModel: ViewModel() {
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch{
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmailAndPassword: ${task.result}")
                        home()
                    }
//                    } else {
//                        Log.d("Firebase", "signInWithEmailAndPassword: ${task.result}")
//                    }
                }.addOnFailureListener { ex ->
                    Log.d("Firebase", "signInWithEmailAndPassword: ${ex.message}")
                }
        } catch (ex: Exception) {
            Log.d("Firebase", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("Firebase", "createUserWithEmailAndPassword: ${task.result}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            id = null
        ).toMap()
//        val user = hashMapOf(
//            "user_id" to userId.toString(),
//            "displayName" to displayName
//        )
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}