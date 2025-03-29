package com.example.mvi_demo.data

import java.io.IOException
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.internal.*
import okio.BufferedSink


class JsonBody(val content: String) : RequestBody() {

    override fun contentType(): MediaType? {
        return JSON
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val bytes = content.toByteArray(charset(CHARSET))
        checkOffsetAndCount(bytes.size.toLong(), 0, bytes.size.toLong())
        sink.write(bytes, 0, bytes.size)
    }

    companion object {
        private val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
        private const val CHARSET = "utf-8"
        fun create(content: String): JsonBody {
            return JsonBody(content)
        }
    }
}