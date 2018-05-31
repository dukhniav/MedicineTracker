package co.iamartem.medicinetracker

/**
 * Created by dukhnia on 5/30/18.
 */
class Medicine {
    var medName: String = ""
    var medDateFilled: String = ""
    var medDosage: Int = 0
    var medRefillQty: Int = 0
    var medDoctor: Doctor = Doctor()
    var medPharmacy: Pharmacy = Pharmacy()


    constructor() {

    }
}
