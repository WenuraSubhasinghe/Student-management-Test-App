package com.test.studentmanagement.service;


import com.test.studentmanagement.dto.AddressDTO;

public interface AddressService {
    AddressDTO getAddressById(Long addressId);
}
