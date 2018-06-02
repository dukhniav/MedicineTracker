package co.iamartem.medicinetracker

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by dukhnia on 5/30/18.
 */

// Doctor object, initially for saving the Users Doctor, however, can
// be saved to an array if more than one doctor exists.
class Doctor() : Parcelable {
    var docName: String = ""
    var docStreet: String = ""
    var docCity: String = ""
    var docState: String = ""
    var docPhone: String = "000-000-0000"

    constructor(parcel: Parcel) : this() {
        docName = parcel.readString()
        docStreet = parcel.readString()
        docCity = parcel.readString()
        docState = parcel.readString()
        docPhone = parcel.readString()
    }


    constructor(docName: String, docStreet: String,
                docCity: String, docState: String, docPhone: String) : this() {
        this.docName = docName
        this.docStreet = docStreet
        this.docCity = docCity
        this.docState = docState
        this.docPhone = docPhone
    }

    constructor(docList: List<String>) : this() {
        this.docName = docList[0]
        this.docStreet = docList[1]
        this.docCity = docList[2]
        this.docState = docList[3]
        this.docPhone = docList[4]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(docName)
        parcel.writeString(docStreet)
        parcel.writeString(docCity)
        parcel.writeString(docState)
        parcel.writeString(docPhone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doctor> {
        override fun createFromParcel(parcel: Parcel): Doctor {
            return Doctor(parcel)
        }

        override fun newArray(size: Int): Array<Doctor?> {
            return arrayOfNulls(size)
        }
    }
}