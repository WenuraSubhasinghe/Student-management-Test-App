package com.test.studentmanagement.mapper;

import com.test.studentmanagement.dto.AddressDTO;
import com.test.studentmanagement.entity.Address;

public class AddressMapper {
    public static AddressDTO mapToAddressDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        return addressDTO;
    }

    public static Address mapToAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        return address;
    }
}
