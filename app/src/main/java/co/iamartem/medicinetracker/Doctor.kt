package co.iamartem.medicinetracker

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by dukhnia on 5/30/18.
 */

// Doctor object, initially for saving the Users Doctor, however, can
// be saved to an array if more than one doctor exists.
class Doctor() : Parcelable {
    var docId: Int = 0
    var docName: String = ""
    var docBus: String = ""
    var docStreet: String = ""
    var docCity: String = ""
    var docState: String = ""
    var docPhone: String = "000-000-0000"

    constructor(parcel: Parcel) : this() {
        docName = parcel.readString()
        docBus = parcel.readString()
        docStreet = parcel.readString()
        docCity = parcel.readString()
        docState = parcel.readString()
        docPhone = parcel.readString()
    }

    constructor(docId: Int, docName: String, docBus: String, docStreet: String,
                docCity: String, docState: String, docPhone: String) : this() {
        this.docId = docId
        this.docName = docName
        this.docBus = docBus
        this.docStreet = docStreet
        this.docCity = docCity
        this.docState = docState
        this.docPhone = docPhone
    }

    constructor(docName: String, docBus: String, docStreet: String,
                docCity: String, docState: String, docPhone: String) : this() {
        this.docName = docName
        this.docBus = docBus
        this.docStreet = docStreet
        this.docCity = docCity
        this.docState = docState
        this.docPhone = docPhone
    }

    constructor(docList: List<String>) : this() {
        this.docName = docList[0]
        this.docBus = docList[1]
        this.docStreet = docList[2]
        this.docCity = docList[3]
        this.docState = docList[4]
        this.docPhone = docList[5]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(docName)
        parcel.writeString(docBus)
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

    // Exists to turn object representation of Doctor object to string when viewed in spinner
    override fun toString(): String {
        return docName
    }
}