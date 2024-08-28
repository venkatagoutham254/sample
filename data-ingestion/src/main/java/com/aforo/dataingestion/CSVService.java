package com.aforo.dataingestion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CSVService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RatePlanRepository ratePlanRepository;

    @Autowired
    private TransactionDataRepository transactionDataRepository;

    @Transactional
    public void readAndStoreCSVData(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             @SuppressWarnings("deprecation")
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                try {
                    // Create and save Customer entity
                    Customer customer = new Customer();
                    customer.setCustomer_id(Integer.parseInt(record.get("customer_id")));
                    customer.setFirst_name(record.get("first_name"));
                    customer.setLast_name(record.get("last_name"));
                    customer.setEmail(record.get("email"));
                    customer.setPhone_number(record.get("phone_number"));
                    customer.setTax_id(record.get("tax_id"));
                    customer.setAddress_line1(record.get("address_line1"));
                    customer.setAddress_line2(record.get("address_line2"));
                    customer.setCity(record.get("city"));
                    customer.setState(record.get("state"));
                    customer.setZip_code(record.get("zip_code"));
                    customer.setCountry(record.get("country"));
                    customer.setRegistartion_date(record.get("registration_date"));
                    customer.setLast_login(record.get("last_login"));
                    customer.setStatus(record.get("status"));
                    customer.setPayment_type(record.get("payment_type"));
                    customerRepository.save(customer);

                    // Create and save RatePlan entity
                    RatePlan ratePlan = new RatePlan();
                    ratePlan.setRate_plan_id(Integer.parseInt(record.get("rate_plan_id")));
                    ratePlan.setRate_plan_name(record.get("rate_plan_name"));
                    ratePlan.setDescription(record.get("description"));
                    ratePlan.setPricing_model(record.get("pricing_model"));
                    ratePlan.setBase_fee(Integer.parseInt(record.get("base_fee")));
                    ratePlan.setTier_min(Integer.parseInt(record.get("tier_min")));
                    ratePlan.setTier_max(Integer.parseInt(record.get("tier_max")));
                    ratePlan.setTier_rate(Integer.parseInt(record.get("tier_rate")));
                    ratePlan.setUsage_fee(Integer.parseInt(record.get("usage_fee")));
                    ratePlan.setCurrency(record.get("currency"));
                    ratePlan.setStart_date(record.get("start_date"));
                    ratePlan.setEnd_date(record.get("end_date"));
                    ratePlan.setStatus(record.get("rate_plan_status"));
                    ratePlanRepository.save(ratePlan);

                    // Create and save TransactionData entity
                    TransactionData transactionData = new TransactionData();
                    transactionData.setCustomer_id(customer.getCustomer_id());
                    transactionData.setFirst_name(customer.getFirst_name());
                    transactionData.setLast_name(customer.getLast_name());
                    transactionData.setTax_id(customer.getTax_id());
                    transactionData.setRate_plan_id(ratePlan.getRate_plan_id());
                    transactionData.setRate_plan_name(ratePlan.getRate_plan_name());
                    transactionData.setDescription(ratePlan.getDescription());
                    transactionData.setBase_fee(ratePlan.getBase_fee());
                    transactionData.setTier_min(ratePlan.getTier_min());
                    transactionData.setTier_max(ratePlan.getTier_max());
                    transactionData.setTier_rate(ratePlan.getTier_rate());
                    transactionData.setUsage_fee(ratePlan.getUsage_fee());
                    transactionData.setCurrency(ratePlan.getCurrency());
                    transactionData.setStart_date(ratePlan.getStart_date());
                    transactionData.setEnd_date(ratePlan.getEnd_date());
                    transactionDataRepository.save(transactionData);

                } catch (Exception e) {
                    System.err.println("Error processing record: " + e.getMessage());
                    // Optional: Log the error or rethrow as a custom exception
                }
            }
        } catch (Exception e) {
            // Handle file-level exceptions
            System.err.println("Error reading CSV file: " + e.getMessage());
            throw e; // rethrow to ensure the error is propagated
        }
    }
}
