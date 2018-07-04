package com.example.dhrumilshah.inventoryappstage1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dhrumilshah.inventoryappstage1.data.BookInventoryContract.BookInventoryEntry;
import com.example.dhrumilshah.inventoryappstage1.data.BookInventoryDBHelper;

public class CatalogActivity extends AppCompatActivity {

    private BookInventoryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new BookInventoryDBHelper(this);
        displayDatabaseInfo();
    }

    private Cursor queryData() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM books"
        // to get a Cursor that contains all rows from the books table.
        String[] projection = {
                BookInventoryEntry._ID,
                BookInventoryEntry.COLUMN_PRODUCT_NAME,
                BookInventoryEntry.COLUMN_PRODUCT_PRICE,
                BookInventoryEntry.COLUMN_PRODUCT_QUANTITY,
                BookInventoryEntry.COLUMN_SUPPLIER_NAME,
                BookInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
        };


        Cursor cursor;
        cursor = db.query(BookInventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the books_inventory database.
     */
    private void displayDatabaseInfo() {

        Cursor cursor = queryData();

        TextView displayView = findViewById(R.id.text_view_books);
        displayView.setText(getString(R.string.the_books_table_contains));
        displayView.append(" " + cursor.getCount() + " ");
        displayView.append(getString(R.string.books) + "\n\n");

        int idColumnIndex = cursor.getColumnIndex(BookInventoryEntry._ID);
        int productNameColumnIndex = cursor.getColumnIndex(BookInventoryEntry.COLUMN_PRODUCT_NAME);
        int productPriceColumnIndex = cursor.getColumnIndex(BookInventoryEntry.COLUMN_PRODUCT_PRICE);
        int productQuantityColumnIndex = cursor.getColumnIndex(BookInventoryEntry.COLUMN_PRODUCT_QUANTITY);
        int supplierNameColumnIndex = cursor.getColumnIndex(BookInventoryEntry.COLUMN_SUPPLIER_NAME);
        int supplierContactColumnIndex = cursor.getColumnIndex(BookInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

        try {
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentProductPrice = cursor.getInt(productPriceColumnIndex);
                int currentProductQuantity = cursor.getInt(productQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierContact = cursor.getString(supplierContactColumnIndex);

                displayView.append("\n" + BookInventoryEntry._ID + "  : " + currentID + "\n" +
                        BookInventoryEntry.COLUMN_PRODUCT_NAME + "  : " + currentProductName + "\n" +
                        BookInventoryEntry.COLUMN_PRODUCT_PRICE + "  : " + currentProductPrice + "\n" +
                        BookInventoryEntry.COLUMN_PRODUCT_QUANTITY + "  : " + currentProductQuantity + "\n" +
                        BookInventoryEntry.COLUMN_SUPPLIER_NAME + "  : " + currentSupplierName + "\n" +
                        BookInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "  : " + currentSupplierContact + "\n");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
