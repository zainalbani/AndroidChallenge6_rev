package com.example.challenge6.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
)