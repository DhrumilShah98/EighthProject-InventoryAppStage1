package com.example.dhrumilshah.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dhrumilshah.inventoryappstage1.data.BookInventoryContract.BookInventoryEntry;

public class BookInventoryDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "books_inventory.db";

    public BookInventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookInventoryEntry.TABLE_NAME + " ("
                + BookInventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookInventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + BookInventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + BookInventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + BookInventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
