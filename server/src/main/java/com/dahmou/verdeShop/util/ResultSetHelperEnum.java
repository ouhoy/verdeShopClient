package com.dahmou.verdeShop.util;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHelperEnum {

    public static <T> T[] getEnumArray(ResultSet rs, String columnName, Class<T> enumType) throws SQLException {
        Array array = rs.getArray(columnName);
        if (array != null) {
            Object[] objectArray = (Object[]) array.getArray();
            return convertToEnumArray(objectArray, enumType);
        }
        return null;
    }

    private static <T> T[] convertToEnumArray(Object[] objectArray, Class<T> enumType) {
        T[] enumArray = (T[]) java.lang.reflect.Array.newInstance(enumType, objectArray.length);
        for (int i = 0; i < objectArray.length; i++) {
            enumArray[i] = getEnumConstant(enumType, String.valueOf(objectArray[i]));
        }
        return enumArray;
    }

    private static <T> T getEnumConstant(Class<T> enumType, String name) {
        if (enumType.isEnum()) {
            try {
                return (T) Enum.valueOf((Class<? extends Enum>) enumType, name);
            } catch (IllegalArgumentException | NullPointerException e) {
                return null;
            }
        }
        return null;
    }

}