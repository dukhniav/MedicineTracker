package co.iamartem.medicinetracker.Object

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by dukhnia on 5/30/18.
 */

// Doctor object, initially for saving the Users Pharmacy, however, can
// be saved to an array if more than one pharmacy exists.
class Pharmacy() : Parcelable {
    var pharId: Int = 0
    var pharName: String = ""
    var pharStreet: String = ""
    var pharCity: String = ""
    var pharState: String = ""
    var pharPhone: String = "000-000-0000"

    constructor(parcel: Parcel) : this() {
        pharName = parcel.readString()
        pharStreet = parcel.readString()
        pharCity = parcel.readString()
        pharState = parcel.readString()
        pharPhone = parcel.readString()
    }

    constructor(pharId: Int, pharName: String, pharStreet: String,
                pharCity: String, pharState: String, pharPhone: String) : this() {
        this.pharId = pharId
        this.pharName = pharName
        this.pharStreet = pharStreet
        this.pharCity = pharCity
        this.pharState = pharState
        this.pharPhone = pharPhone
    }


    constructor(pharName: String, pharStreet: String,
                pharCity: String, pharState: String, pharPhone: String) : this() {
        this.pharName = pharName
        this.pharStreet = pharStreet
        this.pharCity = pharCity
        this.pharState = pharState
        this.pharPhone = pharPhone
    }

    constructor(pharList: List<String>) : this() {
        this.pharName = pharList[0]
        this.pharStreet = pharList[1]
        this.pharCity = pharList[2]
        this.pharState = pharList[3]
        this.pharPhone = pharList[4]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pharName)
        parcel.writeString(pharStreet)
        parcel.writeString(pharCity)
        parcel.writeString(pharState)
        parcel.writeString(pharPhone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pharmacy> {
        override fun createFromParcel(parcel: Parcel): Pharmacy {
            return Pharmacy(parcel)
        }

        override fun newArray(size: Int): Array<Pharmacy?> {
            return arrayOfNulls(size)
        }
    }

    // Exists to turn object representation of Pharmacy object to string when viewed in spinner
    override fun toString(): String {
        return pharName
    }
}