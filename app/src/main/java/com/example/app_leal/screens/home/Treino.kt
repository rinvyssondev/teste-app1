package com.example.app_leal.screens.home

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Treino(
    @Exclude var id: String? = null,
    var nome: String? = null,
    var descricao: String? = null,
    var data: Timestamp? = null,
)

data class Exercicie(
    var nome: String? = null,
    var imagens: List<String>? = null,
    var observacoes: String? = null
)
