package com.example.crmclient.model

data class TheTaches(
    val IdTache : Int,
    val Libelle : String
)
{ override fun toString(): String = Libelle}