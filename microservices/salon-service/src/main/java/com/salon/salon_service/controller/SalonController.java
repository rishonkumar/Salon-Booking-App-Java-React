package com.salon.salon_service.controller;


import com.salon.salon_service.mapper.SalonMapper;
import com.salon.salon_service.modal.Salon;
import com.salon.salon_service.payload.dto.SalonDTO;
import com.salon.salon_service.payload.dto.UserDTO;
import com.salon.salon_service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO>createSalon(@RequestBody SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO,userDTO);
        SalonDTO response = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO>updateSalon(@RequestBody SalonDTO salonDTO,@PathVariable Long salonId) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.updateSalon(salonDTO,userDTO,salonId);
        SalonDTO response = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<SalonDTO>>getSalons(@RequestBody SalonDTO salonDTO,@PathVariable Long salonId) throws Exception {
        List<Salon> salons = salonService.getAllSalon();
        List<SalonDTO>response = salons.stream().map((salon) -> {
            SalonDTO res = SalonMapper.mapToDTO(salon);
            return res;
        }).toList();
        return ResponseEntity.ok(response);
    }

}
