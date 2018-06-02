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
        db?.execSQL(CREATE_DOC_TABLE)
        db?.execSQL(CREATE_PH_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // On upgrade, drop older tables
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DOCTOR")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PHARMACY")

        // Create new tables
        onCreate(db)
    }

    // add doctor to DB
    fun addDoctor(doctor: Doctor){
        val values = ContentValues()

        values.put(COLUMN_DOC_NAME, doctor.docName)
        values.put(COLUMN_DOC_STREET, doctor.docStreet)
        values.put(COLUMN_DOC_CITY, doctor.docCity)
        values.put(COLUMN_DOC_STATE, doctor.docState)
        values.put(COLUMN_DOC_PHONE, doctor.docPhone)

        val db = this.writableDatabase

        db.insert(TABLE_DOCTOR, null, values)
        db.close()
        Log.v("Tag", " DOCTOR Record Inserted Sucessfully")
    }

    // add Pharmacy to DB
    fun addPharmacy(pharmacy: Pharmacy){
        val values = ContentValues()

        values.put(COLUMN_PH_NAME, pharmacy.pharName)
        values.put(COLUMN_PH_STREET, pharmacy.pharStreet)
        values.put(COLUMN_PH_CITY, pharmacy.pharCity)
        values.put(COLUMN_PH_STATE, pharmacy.pharState)
        values.put(COLUMN_PH_PHONE, pharmacy.pharPhone)

        val db = this.writableDatabase

        db.insert(TABLE_PHARMACY, null, values)
        db.close()
        Log.v("Tag", " PHARMACY Record Inserted Sucessfully")
    }

    fun addMed(medicine: Medicine, doctor: Doctor, pharmacy: Pharmacy) {
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
        // Database version
        private val DATABASE_VERSION = 1

        // Database name
        private val DATABASE_NAME = "medicineDB.db"

        // Table names
        private val TABLE_MEDICINE = "medicine"
        private val TABLE_DOCTOR = "doctor"
        private val TABLE_PHARMACY = "pharmacy"

        // Common column names
        private val COLUMN_ID = "_id"
        private val COLUMN_CREATED_AT = "created_at"

        // Medicine table - column names
        private val COLUMN_NAME = "db_name"
        private val COLUMN_QTYREMAINING = "db_qty_rem"
        private val COLUMN_DOSAGE ="db_dosage"
        private val COLUMN_DATEFILL = "db_date_fill"
        private val COLUMN_REFILLQTY = "db_refill_qty"
        private val COLUMN_DOCTOR = "db_doctor"
        private val COLUMN_PHARMACY = "db_pharmacy"

        // Pharmacy table - column names
        private val COLUMN_PH_NAME = "ph_name"
        private val COLUMN_PH_STREET = "ph_street"
        private val COLUMN_PH_CITY = "ph_city"
        private val COLUMN_PH_STATE = "ph_state"
        private val COLUMN_PH_PHONE = "ph_phone"

        // Doctor table - column names
        private val COLUMN_DOC_NAME = "doc_name"
        private val COLUMN_DOC_STREET = "doc_street"
        private val COLUMN_DOC_CITY = "doc_city"
        private val COLUMN_DOC_STATE = "doc_state"
        private val COLUMN_DOC_PHONE = "doc_phone"

        // Table create statements
        //   Medicine create statement
        private val CREATE_MED_TABLE = ("CREATE TABLE "
                + TABLE_MEDICINE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_QTYREMAINING + " INTEGER, "
                + COLUMN_DATEFILL + " TEXT, "
                + COLUMN_DOSAGE + " INTEGER, "
                + COLUMN_REFILLQTY + " INTEGER, "
                + COLUMN_DOCTOR + " TEXT, "
                + COLUMN_PHARMACY + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")

        //   Pharmacy create statement
        private val CREATE_PH_TABLE = ("CREATE TABLE " + TABLE_PHARMACY
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PH_NAME + " TEXT, "
                + COLUMN_PH_STREET + " TEXT, "
                + COLUMN_PH_CITY + " TEXT, "
                + COLUMN_PH_STATE + " TEXT, "
                + COLUMN_PH_PHONE + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")

        //   Doctor create statement
        private val CREATE_DOC_TABLE = ("CREATE TABLE " + TABLE_DOCTOR
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_DOC_NAME + " TEXT, "
                + COLUMN_DOC_STREET + " TEXT, "
                + COLUMN_DOC_CITY + " TEXT, "
                + COLUMN_DOC_STATE + " TEXT, "
                + COLUMN_DOC_PHONE + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")
    }
}