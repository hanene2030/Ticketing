package com.android.kotlin.ticketing

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter (val tickets :List<Ticket>, val itemClickListener: View.OnClickListener):
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      val cardView = itemView.findViewById(R.id.card_view) as CardView
      val titleView = cardView.findViewById(R.id.title) as TextView
      val detailsView = cardView.findViewById(R.id.details) as TextView


  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return ViewHolder(viewItem)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val ticket = tickets[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = ticket.title
        holder.detailsView.text = ticket.details


    }
    override fun getItemCount(): Int {
        return tickets.size //To change body of created functions use File | Settings | File Templates.
    }

}