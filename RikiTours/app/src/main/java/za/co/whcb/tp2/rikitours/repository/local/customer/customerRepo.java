package za.co.whcb.tp2.rikitours.repository.local.customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import za.co.whcb.tp2.rikitours.common.Converter;
import za.co.whcb.tp2.rikitours.config.database.Database;
import za.co.whcb.tp2.rikitours.config.database.table.customer.CustomerTable;
import za.co.whcb.tp2.rikitours.domain.customer.Customer;
import za.co.whcb.tp2.rikitours.factories.customer.CustomerFactory;


/**
 * Created by Game330 on 2016-10-10.
 */
public class customerRepo  extends SQLiteOpenHelper {

    private SQLiteDatabase localDatabase;
    private ContentValues contentValues;
    private static CustomerTable customerTable;


    public customerRepo(Context context) {
        super(context, Database.name, null, Database.version);
        customerTable = new CustomerTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            final String query = Converter.toCreateTableQuery(customerTable.getTableName(), customerTable.getAllAttributes());
            db.execSQL(query);
        } catch (Exception ex) {
            Log.d("SQL ERROR", ex.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + customerTable.getTableName());
        onCreate(db);
    }

    public boolean addCustomer(Customer customer) {
        long returned;
        localDatabase = this.getWritableDatabase();
        customerTable = new CustomerTable();
        contentValues = new ContentValues();

        contentValues.put(customerTable.getAttributeId().name, customer.getId());
        contentValues.put(customerTable.getAttributeName().name, customer.getName());
        contentValues.put(customerTable.getAttributeSurname().name, customer.getName());
        try {
            returned = localDatabase.insert(customerTable.getTableName(), null, contentValues);
        } catch (Exception ex) {
            returned = 0;
            Log.d("exception ::::", ex.getMessage());

        }

        return (returned != -1) ? true : false;
    }

    public Customer findCountryById(long id) {
        Customer customerFound = null;
        localDatabase = this.getReadableDatabase();
        String query = Converter.toSelectAllWhere(customerTable.getTableName(),
                customerTable.getAttributeId(), String.valueOf(id));
        Cursor data = localDatabase.rawQuery(query, null);

        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                customerFound = CustomerFactory.getCustomer(data.getString(1), data.getString(1),
                        data.getString(2));
            }
        }
        return customerFound;
    }

    public ArrayList<Customer> getAllCountries() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customerFound = null;
        localDatabase = this.getReadableDatabase();
        String query = Converter.toSelectAll(customerTable.getTableName());

        Cursor data = localDatabase.rawQuery(query, null);

        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                customerFound = CustomerFactory.getCustomer(data.getString(0), data.getString(1),
                        data.getString(2));
                customers.add(customerFound);
            }
        }

        return customers;
    }

    public boolean updateCustomer(Customer updatedCountry, long id) {

        long returned;
        localDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(customerTable.getAttributeId().name, updatedCountry.getName());
        contentValues.put(customerTable.getAttributeSurname().name, updatedCountry.getName());
        contentValues.put(customerTable.getAttributeSurname().name, updatedCountry.getName());

        try {

            returned = localDatabase.update(customerTable.getTableName(),
                    contentValues, customerTable.getPrimaryKey().name + " = ?",
                    new String[]{String.valueOf(id)});

        } catch (Exception ex) {
            returned = 0;

        }

        return (returned != 0) ? true : false;
    }

    public boolean deleteById(long id) {

        long returned;
        localDatabase = this.getWritableDatabase();

        try {

            returned = localDatabase.delete(customerTable.getTableName(),
                    customerTable.getPrimaryKey().name + " = ?",
                    new String[]{String.valueOf(id)});

        } catch (Exception ex) {
            returned = 0;

        }

        return (returned != 0) ? true : false;

    }
}