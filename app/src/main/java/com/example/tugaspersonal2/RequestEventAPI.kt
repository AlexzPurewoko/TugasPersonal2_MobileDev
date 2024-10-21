package com.example.tugaspersonal2

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ensureActive
import kotlinx.parcelize.Parcelize
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.coroutineContext

object RequestEventAPI {
    private fun okHttpClient(): OkHttpClient
    {
        return OkHttpClient.Builder()
            .build()
    }

    suspend fun getList(): List<Event>
    {
        val reqBuilder = Request.Builder()
            .url("https://event-api.dicoding.dev/events")
            .get()
        val response = okHttpClient().newCall(reqBuilder.build())
            .execute()

        if (response.code != 200) {
            throw Exception("HTTP Failed!")
        }

        val results = Gson().fromJson<ListEventsResponse>(
            response.body!!.string(),
            TypeToken.getParameterized(ListEventsResponse::class.java).type
        )

        if (results.error) {
            throw Exception(results.message)
        }

        coroutineContext.ensureActive()

        return results.listEvents
    }

    fun detailEvent(eventId: Int): Event {
        val reqBuilder = Request.Builder()
            .url("https://event-api.dicoding.dev/events/$eventId")
            .get()
        val response = okHttpClient().newCall(reqBuilder.build())
            .execute()

        if (response.code != 200) {
            throw Exception("HTTP Failed!")
        }

        val results = Gson().fromJson<DetailEventResponse>(
            response.body!!.string(),
            TypeToken.getParameterized(DetailEventResponse::class.java).type
        )

        if (results.error) {
            throw Exception(results.message)
        }


        return results.event
    }

    @Parcelize
    data class Event(
        val id: Int,
        val name: String,
        val summary: String,
        val description: String,
        val imageLogo: String,
        val mediaCover: String,
        val category: String,
        val ownerName: String,
        val cityName: String,
        val quota: Int,
        val registrants: Int,
        val beginTime: String,
        val endTime: String,
        val link: String
    ): Parcelable

    data class DetailEventResponse(
        val error: Boolean,
        val message: String,
        val event: Event
    )

    data class ListEventsResponse(
        val error: Boolean,
        val message: String,
        val listEvents: List<Event>
    )
}