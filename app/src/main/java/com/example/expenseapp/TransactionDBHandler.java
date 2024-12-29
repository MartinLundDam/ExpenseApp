package com.example.expenseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.expenseapp.expenses.ExpenseType;
import com.example.expenseapp.incomes.IncomeType;

public class TransactionDBHandler extends SQLiteOpenHelper {
    //db name and version
    private static final String DB_NAME = "transaction_db";
    private static final int DB_VERSION = 1;

    //tables
    private static final String TABLE_EXPENSES = "expenses";
    private static final String TABLE_INCOMES = "incomes";
    private static final String TABLE_EXPENSE_TYPES = "expense_types";
    private static final String TABLE_INCOME_TYPES = "income_types";

    //common colunm names
    private static final String KEY_ID = "id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DATE = "date";
    private static final String KEY_TYPE_ID = "type_id";

    // Type table column types
    private static final String KEY_TYPE = "type";


    public TransactionDBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_INCOME_TYPES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TYPE + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_EXPENSE_TYPES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TYPE + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_INCOMES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_DESCRIPTION + " TEXT NOT NULL," +
                KEY_AMOUNT + " DOUBLE NOT NULL," +
                KEY_DATE + " TEXT NOT NULL," +
                KEY_TYPE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + KEY_TYPE_ID + ") REFERENCES " + TABLE_INCOME_TYPES + "(" + KEY_ID + "))");

        db.execSQL("CREATE TABLE " + TABLE_EXPENSES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_DESCRIPTION + " TEXT NOT NULL," +
                KEY_AMOUNT + " DOUBLE NOT NULL," +
                KEY_DATE + " TEXT NOT NULL," +
                KEY_TYPE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + KEY_TYPE_ID + ") REFERENCES " + TABLE_INCOME_TYPES + "(" + KEY_ID + "))");

        //insert the enum types into the db
        insertIncomeTypes(db);
        insertExpenseTypes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_TYPES);

        //rebuild tables
        onCreate(db);
    }

    public void addIncome(String description, double amount, String date, int typeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        //values to insert in table
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_DATE, date);
        values.put(KEY_TYPE_ID, typeId);

        db.insert(TABLE_INCOMES, null, values);
    }

    public void addExpense(String description, double amount, String date, int typeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        //values to insert in table
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_DATE, date);
        values.put(KEY_TYPE_ID, typeId);

        db.insert(TABLE_EXPENSES, null, values);
    }

    public void insertIncomeTypes(SQLiteDatabase db) {
        //for each enum in ExpenseTyoe, put it in the TABLE_INCOME_TYPES
        String[] incomeTypes = {"SALARY", "INVESTMENT", "FREELANCE", "OTHER"};
        for (String type : incomeTypes) {
            ContentValues values = new ContentValues();
            values.put(KEY_TYPE, type);
            db.insert(TABLE_INCOME_TYPES, null, values);
        }
    }

    public void insertExpenseTypes(SQLiteDatabase db) {
        for (IncomeType type : IncomeType.values()) {

            ContentValues values = new ContentValues();
            values.put(KEY_TYPE, type.name());
            db.insert(TABLE_INCOME_TYPES, null, values);
        }
    }

}
