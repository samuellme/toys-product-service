package com.jsglobe.toys.service.datasource;

public class DataSourceErrorException extends RuntimeException {
    public DataSourceErrorException(Exception cause) {
        super("DataSource error.", cause);
    }
}
