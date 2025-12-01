package com.jfreed.esg;

import com.jfreed.esg.parser.CsvParser;
import com.jfreed.esg.dto.Customer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTest
{
    private final CsvParser csvParser = new CsvParser();

    @Test
    public void testParseCsv() throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        Path csvPath = Paths.get(Objects.requireNonNull(classLoader.getResource("test.csv")).getPath());
        List<Customer> customers = csvParser.parseCsv(csvPath);
        assertEquals(3, customers.size());
    }
}
