package co.iamartem.medicinetracker

import java.io.Serializable

/**
 * Created by dukhnia on 5/30/18.
 */

// Doctor object, initially for saving the Users Doctor, however, can
// be saved to an array if more than one doctor exists.
class Doctor : Serializable {
    var docName: String = ""
    var docStreet: String = ""
    var docCity: String = ""
    var docState: String = ""
    var docPhone: String = "000-000-0000"

    constructor()

    constructor(docName: String, docStreet: String,
                docCity: String, docState: String, docPhone: String){
        this.docName = docName
        this.docStreet = docStreet
        this.docCity = docCity
        this.docState = docState
        this.docPhone = docPhone
    }
}