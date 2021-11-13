package com.example.tes_pitjarus.data.login

import com.example.tes_pitjarus.data.login.remote.LoginApi
import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import io.reactivex.Single

class LoginDataStore (private val api: LoginApi): LoginRepository {
    override fun postLogin(body: PostLoginBody): Single<PostLoginResponse> {
        return api.postlogin(body.username, body.password).map { it }
    }
}