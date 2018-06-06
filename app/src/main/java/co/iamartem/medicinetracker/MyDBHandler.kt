package co.iamartem.medicinetracker

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
* Created by dukhnia on 5/31/18 !
*/

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // Create table
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_MED_TABLE)
        db?.execSQL(CREATE_DOC_TABLE)
        db?.execSQL(CREATE_PH_TABLE)
        db?.execSQL(CREATE_MED_DOC_PH_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // On upgrade, drop older tables
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DOCTOR")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PHARMACY")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MED_DOC_PH")

        // Create new tables
        onCreate(db)
    }

    //--------------------------------------------------------------------------------------------//
    // add doctor  & pharmacy to DB
    fun addDoctor(doctor: Doctor) : Int {
        Log.v("Tag", " DBHelper -> addDoctor()")
        val values = ContentValues()

        values.put(COLUMN_DOC_NAME, doctor.docName)
        values.put(COLUMN_DOC_STREET, doctor.docStreet)
        values.put(COLUMN_DOC_CITY, doctor.docCity)
        values.put(COLUMN_DOC_STATE, doctor.docState)
        values.put(COLUMN_DOC_PHONE, doctor.docPhone)

        val db = this.writableDatabase

        val doctorID = db.insert(TABLE_DOCTOR, null, values)
        db.close()
        Log.v("Tag", " DOCTOR Record Inserted Sucessfully")

        return doctorID.toInt()
    }

    // add Pharmacy to DB
    fun addPharmacy(pharmacy: Pharmacy){
        Log.v("Tag", " DBHelper -> addPharmacy")

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

    @SuppressLint("Recycle")
//--------------------------------------------------------------------------------------------//
    // check if doctor & pharmacy tables are empty
    fun isDocEmpty() : Boolean{
        Log.v("Tag", " DBHelper -> isDocEmpty()")


        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_DOCTOR", null)
        cursor.moveToFirst()
        val count: Int = cursor.getInt(0)

        Log.v("Tag", " DBHelper -> isDocEmpty returning count: $count")

        return count <= 0
    }

    @SuppressLint("Recycle")
// check if PHARMACY table is empty
    fun isPharEmpty() : Boolean{
        Log.v("Tag", " DBHelper -> isPharEmpty()")

        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_PHARMACY", null)
        cursor.moveToFirst()
        val count: Int = cursor.getInt(0)

        Log.v("Tag", " DBHelper -> isPharEmpty returning count: $count")

        return if (count > 0) {
            Log.v("Tag", " DBHelper -> isPharEmpty returning FALSE")
            false
        }
        else {
            Log.v("Tag", " DBHelper -> isPharEmpty returning TRUE")
            true
        }
    }

    //--------------------------------------------------------------------------------------------//
    // Get a specified doctor / pharmacy
    fun getDoctor(docId : Int) : Doctor {
        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_DOCTOR " +
                " WHERE " + KEY_ID + " = " + docId, null)

        if (cursor != null)
            cursor.moveToFirst()

        val doc : Doctor = Doctor(
                cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOC_STREET)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOC_CITY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOC_STATE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOC_PHONE)))

        cursor.close()
        return doc
    }

    fun getPharmacy(pharmId : Int) : Pharmacy {
        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_DOCTOR " +
                " WHERE " + KEY_ID + " = " + pharmId, null)

        if (true)
            cursor.moveToFirst()

        val pharm : Pharmacy = Pharmacy(
                cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PH_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PH_STREET)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PH_CITY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PH_STATE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PH_PHONE)))

        cursor.close()
        return pharm
    }

    //--------------------------------------------------------------------------------------------//
    // Get ALL doctors & pharmacies
    fun getAllDoctors() : ArrayList<Doctor> {
        val db = this.writableDatabase
        val docArray = ArrayList<Doctor>()

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_DOCTOR", null)

        if(true) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    //val docID = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val docName = cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME))
                    val docStreet = cursor.getString(cursor.getColumnIndex(COLUMN_DOC_STREET))
                    val docCity = cursor.getString(cursor.getColumnIndex(COLUMN_DOC_CITY))
                    val docState = cursor.getString(cursor.getColumnIndex(COLUMN_DOC_CITY))
                    val docPhone = cursor.getString(cursor.getColumnIndex(COLUMN_DOC_PHONE))
                    val doc = Doctor(docName, docStreet, docCity, docState, docPhone)
                    docArray.add(doc)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return docArray
    }
    fun getAllPharmacies(): ArrayList<Pharmacy> {
        val db = this.writableDatabase
        val pharArray = ArrayList<Pharmacy>()

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_PHARMACY", null)

        // TODO: Change to conformity
        if(true) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    //val docID = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val pharName = cursor.getString(cursor.getColumnIndex(COLUMN_PH_NAME))
                    val pharStreet = cursor.getString(cursor.getColumnIndex(COLUMN_PH_STREET))
                    val pharCity = cursor.getString(cursor.getColumnIndex(COLUMN_PH_CITY))
                    val pharState = cursor.getString(cursor.getColumnIndex(COLUMN_PH_CITY))
                    val pharPhone = cursor.getString(cursor.getColumnIndex(COLUMN_PH_PHONE))
                    val phar = Pharmacy(pharName, pharStreet, pharCity, pharState, pharPhone)
                    pharArray.add(phar)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return pharArray
    }

    @Suppress("NAME_SHADOWING")
//--------------------------------------------------------------------------------------------//
    //Add medicine to database from NewPrescriptionActivity
    fun addMed(medicine: Medicine, docIds: IntArray, pharmIds: IntArray) : Int{
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(COLUMN_NAME,         medicine.medName)
        values.put(COLUMN_QTYREMAINING, medicine.medQtyRemaining)
        values.put(COLUMN_DATEFILL,     medicine.medDateFilled)
        values.put(COLUMN_DOSAGE,       medicine.medDosage)
        values.put(COLUMN_REFILLQTY,    medicine.medRefillQty)

        val medId : Long = db.insert(TABLE_MEDICINE, null, values)
        db.close()

        // assign doc and pharmacy to prescription
        docIds.forEach { docId ->
            pharmIds.forEach { pharmId ->
                createMedDocPh(medId.toInt(), docId, pharmId)
            }
        }
        Log.v("Tag", " Medicine Record Inserted Sucessfully")

        return medId.toInt()
    }

//-------------------------- Update medicine -------------------------------------------------//
    fun updateMed(medicine: Medicine, docIds: IntArray, pharmIds: IntArray) : Int {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(COLUMN_NAME,         medicine.medName)
        values.put(COLUMN_QTYREMAINING, medicine.medQtyRemaining)
        values.put(COLUMN_DATEFILL,     medicine.medDateFilled)
        values.put(COLUMN_DOSAGE,       medicine.medDosage)
        values.put(COLUMN_REFILLQTY,    medicine.medRefillQty)


        val medId =  db.update(TABLE_MEDICINE, values, KEY_ID + " = ? ",
                arrayOf<String>((medicine.id).toString()))

        Log.v("Tag", " Medicine Record Updated Sucessfully")

        return medId
    }


    //-------------------------- Get medicine ----------------------------------------------------//
    fun getMedicine(medId: Int) : Medicine{
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM " + TABLE_MEDICINE + " WHERE "
                + KEY_ID + " = " + medId, null)

        if (cursor != null)
            cursor.moveToFirst()

        val med = Medicine(
                cursor.getInt(      cursor.getColumnIndex(KEY_ID)),
                cursor.getString(   cursor.getColumnIndex(COLUMN_NAME)),
                cursor.getInt(      cursor.getColumnIndex(COLUMN_QTYREMAINING)),
                cursor.getString(   cursor.getColumnIndex(COLUMN_DATEFILL)),
                cursor.getInt(      cursor.getColumnIndex(COLUMN_DOSAGE)),
                cursor.getInt(      cursor.getColumnIndex(COLUMN_REFILLQTY)))

        cursor.close()
        return med
    }
    //--------------------------------------------------------------------------------------------//
    //TODO: doctor and pharmacy
    fun getAllCurrentMedicine(): List<Medicine> {
        val db = this.writableDatabase
        val list = ArrayList<Medicine>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_MEDICINE", null)

        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val medID = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val medName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val medQtyRem = cursor.getInt(cursor.getColumnIndex(COLUMN_QTYREMAINING))
                    val medDosage = cursor.getInt(cursor.getColumnIndex(COLUMN_DOSAGE))
                    val medDateFill = cursor.getString(cursor.getColumnIndex(COLUMN_DATEFILL))
                    val medRefillQty = cursor.getInt(cursor.getColumnIndex(COLUMN_REFILLQTY))
                    val med = Medicine(
                            medID,
                            medName,
                            medQtyRem,
                            medDateFill,
                            medDosage,
                            medRefillQty)
                    list.add(med)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return list
    }

    // ------------------------------- Get all meds by doctor/pharmacy ---------------------------//
    //TODO: Implement functionality to return medicine based on who prescribed it or what pharmacy
    //TODO: filled it.
    fun getAllMedByDoc(docName : String) : ArrayList<Medicine> {
        val med = ArrayList<Medicine>()
        return med
    }
    fun getAllMedByPhar(pharName : String) : ArrayList<Pharmacy> {
        val phar = ArrayList<Pharmacy>()
        return phar
    }

    //-------------------------------- Delete medicine from DB -----------------------------------//
    fun deleteMedicine(medId : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_MEDICINE, KEY_ID + " = ?",
                arrayOf((medId).toString()))
    }

    //-------------------------------- Delete doctor from DB -------------------------------------//
    //---------------------------------Delete pharmacy from DB -----------------------------------//

    //TODO: update doctor/pharm, medicine

    //-------------------------------- Assigning doc/pharmacy to Prescription --------------------//
    /**
     * Can also assign multiple doctors/pharmacies to a single medication by calling the function
     * multiple times
     */
    private fun createMedDocPh(medId: Int, docId: Int, pharmId: Int) : Int{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_MED_ID,  medId)
        values.put(KEY_PH_ID,   pharmId)
        values.put(KEY_DOC_ID,  docId)

        val id = db.insert(TABLE_MED_DOC_PH, null, values)

        return id.toInt()
    }

    //------------------------------ Remove doctor / pharmacy from prescription ------------------//


    companion object {
        // Database version
        private const val DATABASE_VERSION = 1

        // Database name
        private const val DATABASE_NAME = "medicineDB.db"

        // Table names
        private const val TABLE_MEDICINE = "medicine"
        private const val TABLE_DOCTOR = "doctor"
        private const val TABLE_PHARMACY = "pharmacy"
        private const val TABLE_MED_DOC_PH = "med_doc_ph"

        // Common column names
        private const val KEY_ID = "_id"
        private const val COLUMN_CREATED_AT = "created_at"

        // NOTES - MED_DOC_PH column names
        private const val KEY_MED_ID = "med_id"
        private const val KEY_DOC_ID = "doc_id"
        private const val KEY_PH_ID = "ph_id"

        // Medicine table - column names
        private const val COLUMN_NAME = "db_name"
        private const val COLUMN_QTYREMAINING = "db_qty_rem"
        private const val COLUMN_DOSAGE ="db_dosage"
        private const val COLUMN_DATEFILL = "db_date_fill"
        private const val COLUMN_REFILLQTY = "db_refill_qty"
        private const val COLUMN_DOCTOR = "db_doctor"
        private const val COLUMN_PHARMACY = "db_pharmacy"

        // Pharmacy table - column names
        private const val COLUMN_PH_NAME = "ph_name"
        private const val COLUMN_PH_STREET = "ph_street"
        private const val COLUMN_PH_CITY = "ph_city"
        private const val COLUMN_PH_STATE = "ph_state"
        private const val COLUMN_PH_PHONE = "ph_phone"

        // Doctor table - column names
        private const val COLUMN_DOC_NAME = "doc_name"
        private const val COLUMN_DOC_STREET = "doc_street"
        private const val COLUMN_DOC_CITY = "doc_city"
        private const val COLUMN_DOC_STATE = "doc_state"
        private const val COLUMN_DOC_PHONE = "doc_phone"

        // Table create statements
        //   Medicine create statement
        private const val CREATE_MED_TABLE = ("CREATE TABLE " + TABLE_MEDICINE
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_QTYREMAINING + " INTEGER, "
                + COLUMN_DATEFILL + " TEXT, "
                + COLUMN_DOSAGE + " INTEGER, "
                + COLUMN_REFILLQTY + " INTEGER, "
                + COLUMN_DOCTOR + " TEXT, "
                + COLUMN_PHARMACY + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")

        //   Pharmacy create statement
        private const val CREATE_PH_TABLE = ("CREATE TABLE " + TABLE_PHARMACY
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PH_NAME + " TEXT, "
                + COLUMN_PH_STREET + " TEXT, "
                + COLUMN_PH_CITY + " TEXT, "
                + COLUMN_PH_STATE + " TEXT, "
                + COLUMN_PH_PHONE + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")

        //   Doctor create statement
        private const val CREATE_DOC_TABLE = ("CREATE TABLE " + TABLE_DOCTOR
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_DOC_NAME + " TEXT, "
                + COLUMN_DOC_STREET + " TEXT, "
                + COLUMN_DOC_CITY + " TEXT, "
                + COLUMN_DOC_STATE + " TEXT, "
                + COLUMN_DOC_PHONE + " TEXT"
                + COLUMN_CREATED_AT + " DATETIME" + ")")

        // Notes - Med_Doc_Ph create statement
        private const val CREATE_MED_DOC_PH_TABLE = ("CREATE TABLE "
                + TABLE_MED_DOC_PH + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_MED_ID + " INTEGER, "
                + KEY_DOC_ID + " INTEGER, "
                + KEY_PH_ID + " INTEGER" + ")")
    }
}