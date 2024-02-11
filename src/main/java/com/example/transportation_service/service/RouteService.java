package com.example.transportation_service.service;

import com.example.transportation_service.dto.RouteDto;
import com.example.transportation_service.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public void createRoute(RouteDto routeDto) {
        routeRepository.createRoute(routeDto);
    }

    public boolean updateRoute(Long id, RouteDto routeDto) {
        return routeRepository.updateRoute(id, routeDto) > 0;
    }

    public boolean deleteRoute(Long id) {
        return routeRepository.deleteRoute(id) > 0;
    }
}
