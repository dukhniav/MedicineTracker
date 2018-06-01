package co.iamartem.medicinetracker

/**
 * Created by dukhnia on 5/30/18.
 */
class Medicine {
    var id: Int = 0
    var medName: String = ""
    var medQtyRemaining: Int = 0
    var medDateFilled: String = ""
    var medDosage: Int = 0
    var medRefillQty: Int = 0
    var medDoctor: Doctor = Doctor()
    var medPharmacy: Pharmacy = Pharmacy()


    constructor() {}

    constructor(
            id: Int,
            medName: String,
            medQtyRemaining: Int,
            medDateFilled: String,
            medDosage: Int,
            medRefillQty: Int,
            medDoctor: Doctor,
            medPharmacy: Pharmacy
    ){
        this.id = id
        this.medName = medName
        this.medQtyRemaining = medQtyRemaining
        this.medDateFilled = medDateFilled
        this.medDosage = medDosage
        this.medRefillQty = medRefillQty
        this.medDoctor = medDoctor
        this.medPharmacy = medPharmacy
    }
}
