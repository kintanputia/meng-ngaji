package com.example.meng_ngaji.helper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {
    @SerializedName("nama")
    @Expose
    var nama: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("no_hp")
    @Expose
    var no_hp: String? = null
}