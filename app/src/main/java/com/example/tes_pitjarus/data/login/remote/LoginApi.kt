package com.example.tes_pitjarus.data.login.remote

import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import io.reactivex.Single

class LoginApi(private val apiClient: LoginApiClient): LoginApiClient {
    override fun postlogin(username: String, password: String): Single<PostLoginResponse> {
        return apiClient.postlogin(username, password)
    }

}