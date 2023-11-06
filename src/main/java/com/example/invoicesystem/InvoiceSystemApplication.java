package com.example.invoicesystem;

import com.example.invoicesystem.model.Invoice;
import com.example.invoicesystem.repository.InvoiceRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class InvoiceSystemApplication implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(InvoiceSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addInvoice(54l, "{\"time\": \"19:53\", \"tenderDetails\": {\"amount\": 23.43, \"type\": \"cash\"}, \"storeNumber\":\"999\"}");
        addInvoice(52l, "{\"time\": \"12:00\", \"tenderDetails\": {\"amount\": 4.95, \"type\": \"cash\"}, \"storeNumber\":\"999\"}");
        addInvoice(56l, "{\"time\": \"08:49\", \"tenderDetails\": {\"amount\": 100.12, \"type\": \"credit\"}, \"storeNumber\":\"999\"}");
    }

    private void addInvoice(Long custormerId, String data) {
        Invoice invoice = new Invoice();
        invoice.setCustomerID(custormerId);
        invoice.setInvoiceData(data);
        invoiceRepository.save(invoice);
    }
}
