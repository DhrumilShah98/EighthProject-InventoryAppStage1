package com.example.dhrumilshah.inventoryappstage1.data;

import android.provider.BaseColumns;

public class BookInventoryContract {

    /* Not instantiable */
    private BookInventoryContract() {
    }

    public static abstract class BookInventoryEntry implements BaseColumns {

        /* Not instantiable */
        private BookInventoryEntry() {
        }

        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}
