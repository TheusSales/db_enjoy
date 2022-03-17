package com.enjoy.Tests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.enjoy.Configs.DbConn;
import com.enjoy.Entities.Consumidor;
import com.enjoy.Entities.Enums.EstadosEnum;

public class InsertTest {

    static DbConn db;

    public static void main(String[] args) throws SQLException {
        startDatabase();
        
        Consumidor consumidor = new Consumidor("987098710","Matheus","46486320877",Calendar.getInstance(),"matheush_sales@hotmail.com");
        
        consumidor.add();

        DbConn.getInstance().close();
    }

    private static void startDatabase() throws SQLException {
        db = DbConn.getInstance();
    }
}
