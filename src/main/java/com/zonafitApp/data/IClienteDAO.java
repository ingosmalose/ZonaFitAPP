package com.zonafitApp.data;

import com.zonafitApp.domain.Customer;
import java.util.List;

public interface IClienteDAO {
    List<Customer> listCustomer();

    boolean searchCustomerById(Customer customer);
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
}
