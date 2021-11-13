package com.example.tes_pitjarus.data.login

import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import io.reactivex.Single

interface LoginRepository {
    fun postLogin(body: PostLoginBody): Single<PostLoginResponse>
}