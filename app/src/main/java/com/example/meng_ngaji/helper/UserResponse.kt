package com.example.meng_ngaji.helper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("data")
    @Expose
    var data: User? = null

    class User {
        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("nama")
        @Expose
        var nama: String? = null

        @SerializedName("no_hp")
        @Expose
        var no_hp: String? = null

        @SerializedName("token")
        @Expose
        var token: String? = null
    }
}