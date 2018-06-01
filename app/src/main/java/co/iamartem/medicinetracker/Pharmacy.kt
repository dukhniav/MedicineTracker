package co.iamartem.medicinetracker

import java.io.Serializable

/**
 * Created by dukhnia on 5/30/18.
 */

// Doctor object, initially for saving the Users Pharmacy, however, can
// be saved to an array if more than one pharmacy exists.
class Pharmacy : Serializable {
    var pharName: String = ""
    var pharStreet: String = ""
    var pharCity: String = ""
    var pharState: String = ""
    var pharPhone: String = "000-000-0000"

    constructor()

    constructor(pharName: String, pharStreet: String,
                pharCity: String, pharState: String, pharPhone: String){
        this.pharName = pharName
        this.pharStreet = pharStreet
        this.pharCity = pharCity
        this.pharState = pharState
        this.pharPhone = pharPhone
    }

    constructor(pharList: List<String>){
        this.pharName = pharList[0]
        this.pharStreet = pharList[1]
        this.pharCity = pharList[2]
        this.pharState = pharList[3]
        this.pharPhone = pharList[4]
    }
}