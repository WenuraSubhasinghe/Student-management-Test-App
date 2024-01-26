package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.AddressDTO;
import com.test.studentmanagement.entity.Address;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.AddressMapper;
import com.test.studentmanagement.repository.AddressRepository;
import com.test.studentmanagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Address doesn't exist with given id: " + addressId));
        AddressDTO addressDTO = AddressMapper.mapToAddressDto(address);
        return addressDTO;
    }
}
