package com.android.kotlin.ticketing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.item_ticket.view.*


class TicketDetailsActivity : AppCompatActivity() {
    companion object{
        val REQUEST_EDIT_TICKET = 1
        val EXTRA_TICKET = "ticket"
        val EXTRA_TICKET_INDEX= "ticketIndex"
        val ACTION_SAVE ="ACTION_SAVE"
        val ACTION_DELETE ="ACTION_DELETE"
    }

    lateinit var ticket:Ticket
    var ticketIndex : Int = -1
    lateinit var titleView :TextView
    lateinit var textView :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

        var toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        ticket = intent.getParcelableExtra<Ticket>(EXTRA_TICKET)
        ticketIndex = intent.getIntExtra(EXTRA_TICKET_INDEX , -1)

        titleView = findViewById(R.id.title) as TextView
        textView = findViewById(R.id.text) as TextView

        titleView.text = ticket.title
        textView.text = ticket.details

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.activity_ticket_details,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save -> {

                saveTicket()

                return true
            }
            R.id.delete -> {

                showConfirmeDeleteDialog()

                return true
            }
            else ->   return super.onOptionsItemSelected(item)


        }

    }
    fun showConfirmeDeleteDialog(){
        val confirmFragment = ConfirmeDelete(ticket.title.toString())
        confirmFragment.listener = object : ConfirmeDelete.ConfirmeDeleteDialogListener{
            override fun onDialogNegativeClick() {

            }

            override fun onDialogPositiveClick() {
                deleteTicket()
            }
        }
        confirmFragment.show(supportFragmentManager, "confirm")

    }


    fun deleteTicket(){



        intent =  Intent(ACTION_DELETE)
        intent.putExtra(EXTRA_TICKET,ticket as Parcelable)
        intent.putExtra(EXTRA_TICKET_INDEX, ticketIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()



    }

    fun saveTicket(){

        ticket.title = titleView.text.toString()
        ticket.details = textView.text.toString()

        intent =  Intent(ACTION_SAVE)
        intent.putExtra(EXTRA_TICKET,ticket as Parcelable)
        intent.putExtra(EXTRA_TICKET_INDEX, ticketIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()



    }
}
