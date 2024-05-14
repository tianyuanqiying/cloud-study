package com.cloud.study.valid.exception;

/**
 * 属性空异常；
 * @author user
 */
public class FieldEmptyException extends RuntimeException {
    private static String FIELD_NAME = "#{FIELD_NAAME}";
    private static String MESSAGE = "field name : [" + FIELD_NAME + "] is not allows empty";
    private FieldEmptyException(String message) {
        super(message);
    }

    public static void throwException(String fieldName) {
        String finalMessage = MESSAGE.replace(FIELD_NAME, fieldName);
        throw new FieldEmptyException(finalMessage);
    }
}
