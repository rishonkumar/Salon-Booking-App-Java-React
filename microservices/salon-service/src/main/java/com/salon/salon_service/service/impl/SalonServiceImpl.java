package com.salon.salon_service.service.impl;

import com.salon.salon_service.modal.Salon;
import com.salon.salon_service.payload.dto.SalonDTO;
import com.salon.salon_service.payload.dto.UserDTO;
import com.salon.salon_service.repository.SalonRepository;
import com.salon.salon_service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO userDTO) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setPhoneNumber(req.getPhoneNumber());
        salon.setImages(req.getImages());
        salon.setOwnerId(req.getOwnerId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {

        Salon exisingSalon = salonRepository.findById(salonId).orElse(null);

        if(exisingSalon != null && salon.getOwnerId().equals(user.getId())) {
            exisingSalon.setName(salon.getName());
            exisingSalon.setAddress(salon.getAddress());
            exisingSalon.setEmail(salon.getEmail());
            exisingSalon.setCity(salon.getCity());
            exisingSalon.setPhoneNumber(salon.getPhoneNumber());
            exisingSalon.setImages(salon.getImages());
            exisingSalon.setOwnerId(salon.getOwnerId());
            exisingSalon.setOpenTime(salon.getOpenTime());
            exisingSalon.setCloseTime(salon.getCloseTime());
            exisingSalon.setOwnerId(String.valueOf(user.getId()));
        }
        throw new Exception("Salon not present");

    }

    @Override
    public List<Salon> getAllSalon() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon =  salonRepository.findById(salonId).orElse(null);

        if(salon == null) {
            throw new Exception("Salong not exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
