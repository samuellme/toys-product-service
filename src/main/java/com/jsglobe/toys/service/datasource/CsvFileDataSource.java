package com.jsglobe.toys.service.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
class CsvFileDataSource implements ProductDataSource {
    private final CsvProductParser csvProductParser;
    private final String datasourceFileName;

    public CsvFileDataSource(
            CsvProductParser csvProductParser,
            @Value("${service.datasource.file_name}") String datasourceFileName
    ) {
        this.csvProductParser = csvProductParser;
        this.datasourceFileName = datasourceFileName;
    }

    @Override
    public List<DataSourceProduct> getAllData() {
        try {
            return csvProductParser.extractFromFile(datasourceFileName);
        } catch (IOException e) {
            throw new DataSourceErrorException(e);
        }
    }
}
