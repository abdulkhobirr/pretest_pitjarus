package com.example.tes_pitjarus.data.login.remote

import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiClient {
    @FormUrlEncoded
    @POST("/api/sariroti_md/index.php/login/loginTest")
    fun postlogin(@Field("username") username:String,
    @Field("password") password: String): Single<PostLoginResponse>
}