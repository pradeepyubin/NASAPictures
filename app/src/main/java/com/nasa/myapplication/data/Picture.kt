package com.nasa.myapplication.data

import java.io.Serializable

data class Picture(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val mediaType: String?,
    val service_version: String,
    val title: String?,
    val url: String?
    ) : Serializable
{
    override fun toString(): String {
        return service_version
    }
}