package co.iamartem.medicinetracker

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by dukhnia on 5/31/18.
 */

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // Create table
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_MED_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINE")
        onCreate(db)
    }

    fun getAllCurrentMedicine(): List<Medicine> {
        val db = this.writableDatabase
        val list = ArrayList<Medicine>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_MEDICINE", null)

//        if (cursor != null) {
//            if (cursor.count > 0) {
//                cursor.moveToFirst()
//                do {
//                    val medID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
//                    val medName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
//                    val medQtyRem = cursor.getInt(cursor.getColumnIndex(COLUMN_QTYREMAINING))
//                    val medDosage = cursor.getInt(cursor.getColumnIndex(COLUMN_DOSAGE))
//                    val medDateFill = cursor.getString(cursor.getColumnIndex(COLUMN_DATEFILL))
//                    val medRefillQty = cursor.getInt(cursor.getColumnIndex(COLUMN_REFILLQTY))
//                    val medDoctor = cursor.(cursor.getColumnIndex(COLUMN_DOCTOR))
//                    val medPharmacy = cursor.getString(cursor.getColumnIndex(COLUMN_PHARMACY))
//                    val med = Medicine(
//                            medID,
//                            medName,
//                            medQtyRem,
//                            medDateFill,
//                            medDosage,
//                            medRefillQty,
//                            medDoctor,
//                            medPharmacy
//                    )
//                    list.add(med)
//                } while (cursor.moveToNext())
//            }
//        }
        cursor.close()
        return list
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