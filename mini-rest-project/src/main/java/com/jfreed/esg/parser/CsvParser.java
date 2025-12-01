package com.jfreed.esg.parser;

import com.jfreed.esg.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
/*
 * TODO: use opencsv library to read CSV file as List<Map<String, String>>
 *  where K=columnName, V=value
 */
public class CsvParser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

    public List<Customer> parseCsv(Path csvFile) throws IOException
    {
        LOGGER.info("Parsing CSV file: {}", csvFile.toAbsolutePath());
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(csvFile))
        {
            String line;

            // Skip header row
            reader.readLine();

            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",", -1);

                if (fields.length != 8)
                {
                    LOGGER.warn("Skipping invalid line: {}", line);
                    continue;
                }

                /*
                 * TODO: Separate component for converting List<Map<String, String>>
                 *  into List<Customer>
                 */
                Customer customer = new Customer(
                        fields[0].trim(),
                        fields[1].trim(),
                        fields[2].trim(),
                        fields[3].trim(),
                        fields[4].trim(),
                        fields[5].trim(),
                        fields[6].trim(),
                        fields[7].trim()
                );
                customers.add(customer);
            }
        }
        return customers;
    }
}
