package com.android.kotlin.ticketing

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmeDelete(val ticketTitle : String ="") :DialogFragment(){


    interface  ConfirmeDeleteDialogListener{
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()

    }
    var listener : ConfirmeDeleteDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you sure you want to delete \n \"$ticketTitle\" ?")
            .setPositiveButton("Delete",DialogInterface.OnClickListener({dialog, id -> listener?.onDialogPositiveClick() }))
            .setNegativeButton("Cancel",DialogInterface.OnClickListener({dialog, id -> listener?.onDialogNegativeClick() }))
        return builder.create()
    }

}