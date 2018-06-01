package co.iamartem.medicinetracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by dukhnia on 5/31/18.
 */

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // Create table
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_MED_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINE")
        onCreate(db)
    }

    fun addMed(medicine: Medicine) {
        val values = ContentValues()

        values.put(COLUMN_NAME,         medicine.medName)
        values.put(COLUMN_QTYREMAINING, medicine.medQtyRemaining)
        values.put(COLUMN_DATEFILL,     medicine.medDateFilled)
        values.put(COLUMN_DOSAGE,       medicine.medDosage)
        values.put(COLUMN_REFILLQTY,    medicine.medRefillQty)
        values.put(COLUMN_DOCTOR,       medicine.medDoctor.toString())
        values.put(COLUMN_PHARMACY,     medicine.medPharmacy.toString())

        val db = this.writableDatabase

        db.insert(TABLE_MEDICINE, null, values)
        db.close()
        Log.v("Tag", " Record Inserted Sucessfully")
    }

    fun getAllCurrentMedicine(): List<Medicine> {
        val db = this.writableDatabase
        val list = ArrayList<Medicine>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_MEDICINE", null)

        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val medID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val medName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val medQtyRem = cursor.getInt(cursor.getColumnIndex(COLUMN_QTYREMAINING))
                    val medDosage = cursor.getInt(cursor.getColumnIndex(COLUMN_DOSAGE))
                    val medDateFill = cursor.getString(cursor.getColumnIndex(COLUMN_DATEFILL))
                    val medRefillQty = cursor.getInt(cursor.getColumnIndex(COLUMN_REFILLQTY))
                    val medDoctor = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR))
                    val medPharmacy = cursor.getString(cursor.getColumnIndex(COLUMN_PHARMACY))
                    val med = Medicine(
                            medID,
                            medName,
                            medQtyRem,
                            medDateFill,
                            medDosage,
                            medRefillQty,
                            toDoctor(medDoctor),
                            toPharmacy(medPharmacy)
                    )
                    list.add(med)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return list
    }

    // Change Doctor & Pharmacy objects to string for storing into DB
    fun toString(doctor: Doctor) : String{
        return "${doctor.docName}%^&${doctor.docStreet}%^&${doctor.docCity}%^&${doctor.docState}%^&${doctor.docPhone}"
    }
    fun toString(pharmacy: Pharmacy) : String{
        return "${pharmacy.pharName}%^&${pharmacy.pharStreet}%^&${pharmacy.pharCity}%^&${pharmacy.pharState}%^&${pharmacy.pharPhone}"
    }

    // Change string representation of Doctor & Pharmacy back to Object
    fun toDoctor(doctorStr: String) : Doctor{
        val docList = doctorStr.split("%^&".toRegex())
        return Doctor(docList)
    }
    fun toPharmacy(pharmacyStr: String) : Pharmacy{
        val pharList = pharmacyStr.split("%^&".toRegex())
        return Pharmacy(pharList)
    }
    
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "medicineDB.db"
        val TABLE_MEDICINE = "medicine"

        val COLUMN_ID = "_id"
        val COLUMN_NAME = "db_name"
        val COLUMN_QTYREMAINING = "db_qtyremaining"
        val COLUMN_DOSAGE ="db_dosage"
        val COLUMN_DATEFILL = "db_datefill"
        val COLUMN_REFILLQTY = "db_refillqty"
        val COLUMN_DOCTOR = "db_doctor"
        val COLUMN_PHARMACY = "db_pharmacy"

        val CREATE_MED_TABLE = ("CREATE TABLE "
                + TABLE_MEDICINE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_QTYREMAINING + " INTEGER, "
                + COLUMN_DATEFILL + " TEXT, "
                + COLUMN_DOSAGE + " INTEGER, "
                + COLUMN_REFILLQTY + " INTEGER, "
                + COLUMN_DOCTOR + " TEXT, "
                + COLUMN_PHARMACY + " TEXT" + ")")

    }
}