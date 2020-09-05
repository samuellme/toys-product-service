package com.jsglobe.toys.service.datasource;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
class CsvProductParser {

    public List<DataSourceProduct> extractFromFile(String datasourceFileName) throws IOException {
        try (var file = new FileReader(datasourceFileName)) {
            return extractContent(file);
        }
    }

    private List<DataSourceProduct> extractContent(Reader contentReader) throws IOException {
        final CsvSchema csvSchema = CsvSchema
                .emptySchema()
                .withHeader();

        return new CsvMapper()
                .readerFor(DataSourceProduct.class)
                .with(csvSchema)
                .readValues(contentReader)
                .readAll()
                .stream()
                .map(product -> (DataSourceProduct) product)
                .collect(toList());
    }
}


