package io.fnska.booking.converter;

import io.fnska.booking.converter.dto.CustomerDto;
import io.fnska.booking.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerDto> {
    @Override
    public CustomerDto entityToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setSurname(customer.getSurname());
        dto.setEmail(customer.getEmail());
        return dto;
    }

    @Override
    public Customer dtoToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    @Override
    public Page<CustomerDto> entityToDto(Page<Customer> customer) {
        return customer.map(this::entityToDto);
    }
}
