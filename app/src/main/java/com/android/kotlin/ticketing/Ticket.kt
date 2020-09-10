package com.android.kotlin.ticketing

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
//j ai rajouté Serializable pour pouvoir stocker le résultat dans un fichier
data class Ticket(
    var title: String? ="",
    var details: String? ="",
    var fileName: String? ="" ):Parcelable,Serializable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(details)
        parcel.writeString(fileName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ticket> {
        private val serialVersionUid :Long = 9999999999
        override fun createFromParcel(parcel: Parcel): Ticket {
            return Ticket(parcel)
        }

        override fun newArray(size: Int): Array<Ticket?> {
            return arrayOfNulls(size)
        }
    }


}