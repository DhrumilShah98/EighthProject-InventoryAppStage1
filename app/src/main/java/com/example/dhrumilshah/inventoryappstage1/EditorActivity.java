package com.example.dhrumilshah.inventoryappstage1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhrumilshah.inventoryappstage1.data.BookInventoryContract.BookInventoryEntry;
import com.example.dhrumilshah.inventoryappstage1.data.BookInventoryDBHelper;

public class EditorActivity extends AppCompatActivity {

    public BookInventoryDBHelper dbHelper;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQuantityEditText;
    private EditText supplierNameEditText;
    private EditText supplierContactEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        productNameEditText = findViewById(R.id.product_name);
        productPriceEditText = findViewById(R.id.poduct_price);
        productQuantityEditText = findViewById(R.id.product_quantity);
        supplierNameEditText = findViewById(R.id.supplier_name);
        supplierContactEditText = findViewById(R.id.supplier_contact);
        dbHelper = new BookInventoryDBHelper(this);
    }

    private void insertBook() {
        String productName;
        if (TextUtils.isEmpty(productNameEditText.getText())) {
            productNameEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productName = productNameEditText.getText().toString().trim();
        }

        String productPrice;
        if (TextUtils.isEmpty(productPriceEditText.getText())) {
            productPriceEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productPrice = productPriceEditText.getText().toString().trim();
        }

        String productQuantity;
        if (TextUtils.isEmpty(productQuantityEditText.getText())) {
            productQuantityEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productQuantity = productQuantityEditText.getText().toString().trim();
        }

        String supplierName;
        if (TextUtils.isEmpty(supplierNameEditText.getText())) {
            supplierNameEditText.setError(getString(R.string.required_field));
            return;
        } else {
            supplierName = supplierNameEditText.getText().toString().trim();
        }

        String supplierContact;
        if (TextUtils.isEmpty(supplierContactEditText.getText())) {
            supplierContactEditText.setError(getString(R.string.required_field));
            return;
        } else {
            supplierContact = supplierContactEditText.getText().toString().trim();
        }

        int productPriceInt = Integer.parseInt(productPrice);
        if(productPriceInt < 0){
            productPriceEditText.setError(getString(R.string.price_cannot_be_negative));
            Toast.makeText(this, getString(R.string.price_cannot_be_negative), Toast.LENGTH_SHORT).show();
            return;
        }
        int productQuantityInt = Integer.parseInt(productQuantity);
        if(productQuantityInt < 0){
            productQuantityEditText.setError(getString(R.string.quantity_cannot_be_negative));
            Toast.makeText(this, getString(R.string.quantity_cannot_be_negative), Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookInventoryEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(BookInventoryEntry.COLUMN_PRODUCT_PRICE, productPriceInt);
        values.put(BookInventoryEntry.COLUMN_PRODUCT_QUANTITY, productQuantityInt);
        values.put(BookInventoryEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(BookInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierContact);

        long newRowId = db.insert(BookInventoryEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, getString(R.string.error_saving_book), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.book_saved) + " " + newRowId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertBook();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
