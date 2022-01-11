package com.example.meng_ngaji.response

import com.example.meng_ngaji.nearby.ModelResults
import com.google.gson.annotations.SerializedName

class ModelResultNearby {
    @SerializedName("results")
    lateinit var modelResults: List<ModelResults>
}