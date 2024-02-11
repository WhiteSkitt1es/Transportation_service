package com.example.transportation_service.service;

import com.example.transportation_service.dto.CarrierDto;
import com.example.transportation_service.repository.CarrierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarrierService {

    private final CarrierRepository carrierRepository;

    public void createCarrier(CarrierDto carrierDto) {
        carrierRepository.createCarrier(carrierDto);
    }

    public boolean updateCarrier(Long id, CarrierDto carrierDto) {
        return carrierRepository.updateCarrier(id, carrierDto) > 0;
    }

    public boolean deleteCarrier(Long id) {
        return carrierRepository.deleteCarrier(id) > 0;
    }
}
