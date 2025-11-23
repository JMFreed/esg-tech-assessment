package com.jfreed.esg.parser;

import com.jfreed.esg.dto.Customer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParser
{
    public List<Customer> parseCsv(Path csvFile) throws IOException
    {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(csvFile))
        {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",", -1);

                if (fields.length != 8)
                {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

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
