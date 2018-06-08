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


    constructor() {}

    constructor(medName: String, medQtyRemaining: Int, medDateFilled: String, medDosage: Int,
                medRefillQty: Int){
        this.medName = medName
        this.medQtyRemaining = medQtyRemaining
        this.medDateFilled = medDateFilled
        this.medDosage = medDosage
        this.medRefillQty = medRefillQty
    }

    constructor(id: Int, medName: String, medQtyRemaining: Int, medDateFilled: String, medDosage: Int,
                medRefillQty: Int){
        this.id = id
        this.medName = medName
        this.medQtyRemaining = medQtyRemaining
        this.medDateFilled = medDateFilled
        this.medDosage = medDosage
        this.medRefillQty = medRefillQty
    }
}
