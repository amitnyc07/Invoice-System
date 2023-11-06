package com.example.invoicesystem.repository;

import com.example.invoicesystem.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomerID(Long customerID);
}
