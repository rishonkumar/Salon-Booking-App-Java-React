package com.salon.salon_service.service;

import com.salon.salon_service.modal.Salon;
import com.salon.salon_service.payload.dto.SalonDTO;
import com.salon.salon_service.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO userDTO);

    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception;

    List<Salon> getAllSalon();

    Salon getSalonById(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon>searchSalonByCity(String city);
}
