package com.example.tugaspersonal2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

typealias EventListItemClick = (event: RequestEventAPI.Event) -> Unit

class EventListAdapter(
    private val eventList: List<RequestEventAPI.Event>,
    private val onItemClicked: EventListItemClick
): RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_list_item, parent, false)
        return EventListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.setContent(eventList[position], onItemClicked)
    }

    class EventListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mediaCover: ImageView = itemView.findViewById(R.id.media_cover)
        val eventTitle: TextView = itemView.findViewById(R.id.event_title)
        val summaryContent: TextView = itemView.findViewById(R.id.summary_content)
        val btnDetail: TextView = itemView.findViewById(R.id.gotoDetail)

        fun setContent(event: RequestEventAPI.Event, onItemClicked: EventListItemClick) {
            Glide.with(itemView.context)
                .load(event.mediaCover)
                .into(mediaCover)

            eventTitle.text = event.name
            summaryContent.text = event.summary
            btnDetail.setOnClickListener {
                onItemClicked.invoke(event)
            }
        }
    }
}