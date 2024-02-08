package com.example.busbooking.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.busbooking.model.tickets

class TicketDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TicketDB"
        private const val TABLE_TICKETS = "tickets"
        private const val KEY_ID = "id"
        private const val KEY_FROM = "from_place"
        private const val KEY_TO = "to_place"
        private const val KEY_DATE = "date"
        private const val KEY_PRICE = "price"
        private const val KEY_TIME = "time"
        private const val KEY_COUNT = "count"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery =
            ("CREATE TABLE $TABLE_TICKETS($KEY_ID INTEGER PRIMARY KEY,$KEY_FROM TEXT,$KEY_TO TEXT,$KEY_DATE TEXT,$KEY_PRICE INTEGER,$KEY_TIME TEXT,$KEY_COUNT INTEGER,$KEY_NAME TEXT,$KEY_PHONE TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TICKETS")
        onCreate(db)
    }

    fun addTicket(ticket: tickets) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_FROM, ticket.from)
        values.put(KEY_TO, ticket.to)
        values.put(KEY_DATE, ticket.date)
        values.put(KEY_PRICE, ticket.price)
        values.put(KEY_TIME, ticket.time)
        values.put(KEY_COUNT, ticket.count)
        values.put(KEY_NAME, ticket.name)
        values.put(KEY_PHONE, ticket.phone)

        db.insert(TABLE_TICKETS, null, values)
//        db.close()
    }

    @SuppressLint("Range")
    fun getAllTickets(): List<tickets> {
        val ticketList = mutableListOf<tickets>()
        val selectQuery = "SELECT * FROM $TABLE_TICKETS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val ticket = tickets(
                    cursor.getString(cursor.getColumnIndex(KEY_FROM)),
                    cursor.getString(cursor.getColumnIndex(KEY_TO)),
                    cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                    cursor.getInt(cursor.getColumnIndex(KEY_PRICE)),
                    cursor.getString(cursor.getColumnIndex(KEY_TIME)),
                    cursor.getInt(cursor.getColumnIndex(KEY_COUNT)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                )
                ticketList.add(ticket)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return ticketList


    }

    fun deleteAllTickets() {
        val db = this.writableDatabase
        db.delete(TABLE_TICKETS, null, null)
        db.close()
    }
}

