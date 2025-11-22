package com.jfreed.esg;

import com.jfreed.esg.customer.CustomerService;
import com.jfreed.esg.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner
{
    @Autowired
    private CsvParser csvParser;

    @Autowired
    private CustomerService customerService;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {

        if (args.length != 1)
        {
            System.err.println("Error: Please provide the path to a CSV file as the only argument.");
            System.exit(1);
        }

        String csvPathArg = args[0];
        Path csvPath = Path.of(csvPathArg).toAbsolutePath().normalize();

        if (!Files.exists(csvPath) || !Files.isRegularFile(csvPath))
        {
            System.err.println("Error: The file '" + csvPathArg + "' does not exist or is not a regular file.");
            System.exit(1);
        }

        if (!csvPathArg.toLowerCase().endsWith(".csv"))
        {
            System.err.println("Error: The file '" + csvPath + "' is not a CSV file.");
            System.exit(1);
        }

        System.out.println("CSV file is valid: " + csvPath);

        List<Customer> customers = csvParser.parseCsv(csvPath);

        customers.forEach(customer -> {
            String response = customerService.createCustomer(customer);
            System.out.println("Saved customer: " + response);
        });

        List<Customer> all = customerService.getCustomers();
        System.out.println(all);

        String customerRef = "12345";
        Customer customer = customerService.getCustomer(customerRef);
        System.out.println(customer);

        System.exit(0);
    }
}
