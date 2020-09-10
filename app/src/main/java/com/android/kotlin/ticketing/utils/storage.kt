package com.android.kotlin.ticketing.utils

import android.content.Context
import android.text.TextUtils
import com.android.kotlin.ticketing.Ticket
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

private val TAG="storage"
fun persistTicket(context : Context, ticket : Ticket ){

    if(TextUtils.isEmpty(ticket.fileName)){
        ticket.fileName = UUID.randomUUID().toString()+ ".ticket"

    }
    val fileOutput =context.openFileOutput(ticket.fileName, Context.MODE_PRIVATE)
    val objectOutputStream = ObjectOutputStream(fileOutput)
    objectOutputStream.writeObject(ticket)
    objectOutputStream.close()


}

fun loadTickets(context :Context) : MutableList<Ticket>{

    val tickets = mutableListOf<Ticket>()
    val ticketDir =context.filesDir
    for(   fileName in  ticketDir.list()){
        val ticket = loadTicket(context, fileName)
        tickets.add(ticket)

    }
    return tickets

}

fun persistDeleteTicket(context: Context, ticket: Ticket){
    context.deleteFile(ticket.fileName)
}
private fun loadTicket(context :Context , fileName :String ): Ticket{

    val fileIntput =context.openFileInput(fileName)
    val objectInputStream = ObjectInputStream(fileIntput)
    val ticket = objectInputStream.readObject() as Ticket
    objectInputStream.close()
    return ticket


}