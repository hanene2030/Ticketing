package com.android.kotlin.ticketing

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlin.ticketing.utils.loadTickets
import com.android.kotlin.ticketing.utils.persistDeleteTicket
import com.android.kotlin.ticketing.utils.persistTicket
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var tickets : MutableList<Ticket>
    lateinit var adapter: TicketAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

    var ft =    findViewById(R.id.create_ticket) as  FloatingActionButton
        ft.setOnClickListener(this)

        tickets = loadTickets(this)//mutableListOf<Ticket>()
     /**   tickets.add(Ticket("Ticket 1", "Here desc"))
        tickets.add(Ticket("Ticket 2", "Here desc"))
        tickets.add(Ticket("Ticket 3", "Here desc"))
        tickets.add(Ticket("Ticket 4", "Here desc"))**/
        adapter = TicketAdapter(tickets, this)
        val recyclerView = findViewById(R.id.tickets_recycle_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onClick(view: View) {
       if(view.tag != null){
           Log.i(" Ticket List Activity ","Click on note")
           showTicketDetails(view.tag as Int)
       }else{
           when(view.id){
               R.id.create_ticket -> createNewTicket()

           }
       }

         }
    fun createNewTicket(){
        showTicketDetails(-1 )
    }

    fun showTicketDetails(ticketIndex : Int ){

        val ticket = if(ticketIndex <0)  Ticket() else tickets[ticketIndex]
        val intent = Intent (this, TicketDetailsActivity::class.java)
        intent.putExtra(TicketDetailsActivity.EXTRA_TICKET  ,ticket as Parcelable)
        intent.putExtra(TicketDetailsActivity.EXTRA_TICKET_INDEX,ticketIndex)
        startActivityForResult(intent, TicketDetailsActivity.REQUEST_EDIT_TICKET)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode!= Activity.RESULT_OK || data ==null){
            return
        }
        when(requestCode){
            TicketDetailsActivity.REQUEST_EDIT_TICKET -> {

                println("=====================================")
                proceedSaveTicketResult(data)



            }
        }


    }
   private fun proceedSaveTicketResult(data: Intent){
       val ticketIndex = data.getIntExtra(TicketDetailsActivity.EXTRA_TICKET_INDEX , -1)

       when(data.action){
           TicketDetailsActivity.ACTION_SAVE ->{
               val ticket = data.getParcelableExtra<Ticket>(TicketDetailsActivity.EXTRA_TICKET )
               saveTicket(ticketIndex,ticket)
           }
           TicketDetailsActivity.ACTION_DELETE ->{
               deleteTicket(ticketIndex)
           }
       }



   }
private fun saveTicket(ticketIndex: Int,ticket:Ticket){
    persistTicket(this,ticket)
    if(ticketIndex<0){
        tickets.add(0, ticket)
    }else {
        tickets[ticketIndex] = ticket
    }
    adapter.notifyDataSetChanged()

}
    private fun deleteTicket(ticketIndex: Int){
        if(ticketIndex<0){
            return
        }
          val ticket =  tickets.removeAt(ticketIndex)
        persistDeleteTicket(this, ticket);

        adapter.notifyDataSetChanged()

    }



}
