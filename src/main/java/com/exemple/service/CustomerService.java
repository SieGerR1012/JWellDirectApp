package com.exemple.service;

import com.exemple.model.Customer;
import com.exemple.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        Customer existing = findById(id);
        existing.setName(customer.getName());
        return repository.save(existing);
    }

    public Customer patch(Long id, Customer customer) {
        Customer existing = findById(id);
        if (customer.getName() != null) {
            existing.setName(customer.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Customer existing = findById(id);
        repository.delete(existing);
    }
}
