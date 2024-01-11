package com.dahmou.verdeShop.util;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHelper {

    public static String[] getStringArray(ResultSet rs, String columnName) throws SQLException {
        Array array = rs.getArray(columnName);
        if (array != null) {
            Object[] objectArray = (Object[]) array.getArray();
            return convertToStringArray(objectArray);
        }
        return null;
    }

    private static String[] convertToStringArray(Object[] objectArray) {
        String[] stringArray = new String[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            stringArray[i] = String.valueOf(objectArray[i]);
        }
        return stringArray;
    }
}