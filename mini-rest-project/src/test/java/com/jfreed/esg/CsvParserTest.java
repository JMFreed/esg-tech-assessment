package com.jfreed.esg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTest
{
    private CsvParser csvParser = new CsvParser();

    @Test
    public void testParseCsv() throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        Path csvPath = Paths.get(classLoader.getResource("test.csv").getPath());
        List<Customer> customers = csvParser.parseCsv(csvPath);
        assertEquals(3, customers.size());
    }
}
