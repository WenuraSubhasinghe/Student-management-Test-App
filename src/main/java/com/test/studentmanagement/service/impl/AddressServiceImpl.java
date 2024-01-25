package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.AdressDto;
import com.test.studentmanagement.entity.Address;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.AddressMapper;
import com.test.studentmanagement.repository.AddressRepository;
import com.test.studentmanagement.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    @Override
    public AdressDto getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Address doesn't exist with given id: " + addressId));
        return AddressMapper.mapToAddressDto(address);
    }
}
