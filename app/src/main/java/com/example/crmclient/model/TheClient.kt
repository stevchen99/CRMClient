package com.example.crmclient.model

data class TheClient(

    val IdClient: Int,
    val Company: String
)
{ override fun toString(): String = Company}