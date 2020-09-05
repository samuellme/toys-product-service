package com.jsglobe.toys.service.datasource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Log4j2(topic = "CsvFileDataSource")
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
            log.info("Data Source file reading started.");
            return csvProductParser.extractFromFile(datasourceFileName);
        } catch (IOException e) {
            log.error("data source extracting error", e);
            throw new DataSourceErrorException(e);
        } finally {
            log.info("Data Source file reading finished.");
        }
    }
}
