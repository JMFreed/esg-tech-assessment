package com.jfreed.esg;

import com.jfreed.esg.customer.CustomerService;
import com.jfreed.esg.dto.Customer;
import com.jfreed.esg.parser.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ConsoleApp implements CommandLineRunner
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    @Autowired
    private CsvParser csvParser;

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception
    {

        if (args.length != 1)
        {
            LOGGER.error("Please provide path to CSV file as the only argument");
            System.exit(1);
        }

        String csvPathArg = args[0];
        Path csvPath = Path.of(csvPathArg).toAbsolutePath().normalize();

        if (!Files.exists(csvPath) || !Files.isRegularFile(csvPath))
        {
            LOGGER.error("File {} does not exist or is not a regular file", csvPathArg);
            System.exit(1);
        }

        if (!csvPathArg.toLowerCase().endsWith(".csv"))
        {
            LOGGER.error("{} is not a CSV file", csvPath);
            System.exit(1);
        }

        List<Customer> customers = csvParser.parseCsv(csvPath);

        customers.forEach(customer -> {
            String response = customerService.createCustomer(customer);
            LOGGER.info("Saved customer: {}", response);
        });

        LOGGER.info("Calling GET /api/v1/customers");
        List<Customer> all = customerService.getCustomers();
        LOGGER.info("Result set: {}", all);

        String customerRef = "12345";
        LOGGER.info("Calling GET /api/v1/customers?customerRef={}", customerRef);
        Customer customer = customerService.getCustomer(customerRef);
        LOGGER.info("Result set: {}", customer);

        System.exit(0);
    }
}
