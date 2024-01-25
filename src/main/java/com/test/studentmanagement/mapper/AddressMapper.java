package com.test.studentmanagement.mapper;

import com.test.studentmanagement.dto.AdressDto;
import com.test.studentmanagement.entity.Address;
public class AddressMapper {
    public static AdressDto mapToAddressDto(Address address){

        if(address == null){
            return null;
        }
        AdressDto adressDto = new AdressDto();
        adressDto.setId(address.getId());
        adressDto.setStreet(address.getStreet());
        adressDto.setCity(address.getCity());
        return adressDto;
    }
    public static Address mapToAddress(AdressDto adressDto){
        if(adressDto == null){
            return null;
        }
        Address address = new Address();
        address.setId(adressDto.getId());
        address.setStreet(adressDto.getStreet());
        address.setCity(adressDto.getCity());
        return address;
    }
}
