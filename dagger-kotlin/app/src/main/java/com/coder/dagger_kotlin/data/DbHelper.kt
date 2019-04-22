package com.coder.dagger_kotlin.data

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.coder.dagger_kotlin.di.ApplicationContext
import com.coder.dagger_kotlin.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Singleton
import android.content.ContentValues
import android.content.res.Resources
import com.coder.dagger_kotlin.data.model.User
import android.content.res.Resources.NotFoundException
import android.database.Cursor
import android.util.Log

@Singleton
class DbHelper @Inject constructor(
    @ApplicationContext var context: Context,
    @DatabaseInfo var dbName: String,
    @DatabaseInfo var version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {


    companion object {

        const val USER_TABLE_NAME = "users"
        const val USER_COLUMN_USER_ID = "id"
        const val USER_COLUMN_USER_NAME = "usr_name"
        const val USER_COLUMN_USER_ADDRESS = "usr_add"
        const val USER_COLUMN_USER_CREATED_AT = "created_at"
        const val USER_COLUMN_USER_UPDATED_AT = "updated_at"

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        tableCreateStatements(db)
    }

    private fun tableCreateStatements(db: SQLiteDatabase?) {

        try {
            val create_table = "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY, " +
                    USER_COLUMN_USER_NAME + " TEXT, " +
                    USER_COLUMN_USER_ADDRESS + " TEXT, " +
                    USER_COLUMN_USER_CREATED_AT + " TEXT, " +
                    USER_COLUMN_USER_UPDATED_AT + " TEXT)"
            Log.d("DBHELPER", create_table)
            db?.execSQL(
                create_table
            )

        } catch (e: SQLException) {
            e.printStackTrace();
            Log.e("DbHelper", "Error creating table")
        }

    }

    @Throws(Exception::class)
    fun insertUser(user: User) {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(USER_COLUMN_USER_ID, user.id)
            contentValues.put(USER_COLUMN_USER_NAME, user.name)
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.address)
            contentValues.put(USER_COLUMN_USER_CREATED_AT, getCurrentTimeStamp())
            contentValues.put(USER_COLUMN_USER_UPDATED_AT, getCurrentTimeStamp())
            db.insert(USER_TABLE_NAME, null, contentValues)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User {
        var cursor: Cursor? = null
        try {
            val db = this.readableDatabase
            cursor = db.rawQuery(
                "SELECT * FROM "
                        + USER_TABLE_NAME
                        + " WHERE "
                        + USER_COLUMN_USER_ID
                        + " = ? ",
                arrayOf(userId.toString())
            )

            if (cursor.count > 0) {
                cursor.moveToFirst()
                val user = User()
                user.id = cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID))
                user.name = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME))
                user.address = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS))
                user.createdAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT))
                user.updatedAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT))
                db.close()
                return user
            } else {
                throw NotFoundException("User with id $userId does not exists")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            throw e
        } finally {
            cursor?.close()
        }
    }

    private fun getCurrentTimeStamp() = (System.currentTimeMillis() / 1000).toString()


}
