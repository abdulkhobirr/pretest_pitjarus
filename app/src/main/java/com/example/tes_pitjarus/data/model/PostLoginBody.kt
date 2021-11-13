package com.example.tes_pitjarus.data.model

import com.google.gson.annotations.SerializedName

data class PostLoginBody(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)