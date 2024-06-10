package com.tihonyakovlev.rehabilitationapp.domain

import android.content.Context
import com.google.gson.Gson
import com.tihonyakovlev.rehabilitationapp.data.Content
import java.io.IOException

object JsonUtils {
    fun loadJSONFromAsset(context: Context, fileName: String): String {
        val json: String?
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    fun parseContent(json: String): Content {
        val gson = Gson()
        return gson.fromJson(json, Content::class.java)
    }
}
