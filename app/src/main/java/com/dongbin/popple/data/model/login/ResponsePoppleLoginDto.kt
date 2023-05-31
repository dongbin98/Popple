package com.dongbin.popple.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponsePoppleLoginDto(
    @SerializedName("access_token")
    @Expose
    val accessToken: String?,

    @SerializedName("token_type")
    @Expose
    val tokenType: String?,

    @SerializedName("username")
    @Expose
    val userName: String?,
)