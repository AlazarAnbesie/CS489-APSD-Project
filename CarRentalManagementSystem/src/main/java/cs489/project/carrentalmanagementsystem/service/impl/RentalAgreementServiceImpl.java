package cs489.project.carrentalmanagementsystem.service.impl;

import cs489.project.carrentalmanagementsystem.dto.rent.request.RentalAgreementRequest;
import cs489.project.carrentalmanagementsystem.dto.rent.response.RentalAgreementResponse;
import cs489.project.carrentalmanagementsystem.exception.RentalAgreementException;
import cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement;
import cs489.project.carrentalmanagementsystem.model.rent.enums.RentalStatus;
import cs489.project.carrentalmanagementsystem.repository.RentalRepository;
import cs489.project.carrentalmanagementsystem.service.RentalAgreementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalAgreementServiceImpl implements RentalAgreementService {
    private final RentalRepository rentalRepository;

    private final ModelMapper modelMapper;

    public RentalAgreementServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RentalAgreementResponse> getAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(rental -> modelMapper
                        .map(rental, RentalAgreementResponse.class))
                .toList();
    }

    @Override
    public RentalAgreementResponse getRentalById(Long var1) {
        return modelMapper.map(rentalRepository.findById(var1).
                orElseThrow(()->new RentalAgreementException("Rent with id "+var1+" is not found")), RentalAgreementResponse.class);
    }

    @Override
    public RentalAgreementResponse createRental(RentalAgreementRequest var1) {
        RentalAgreement rental = modelMapper.map(var1, RentalAgreement.class);
        RentalAgreement savedRental = rentalRepository.save(rental);
        return modelMapper.map(savedRental, RentalAgreementResponse.class);
    }

    @Override
    public RentalAgreementResponse updateRental(RentalAgreementRequest var1, Long var2) {
        RentalAgreement rental = rentalRepository.findById(var2)
                .orElseThrow(()->new RentalAgreementException("Rent with id "+var2+" is not found"));
        rental.setTotalCost(var1.getTotalCost());
        rental.setStartDate(var1.getStartDate());
        rental.setEndDate(var1.getEndDate());
        rental.setStatus(var1.getStatus());
        return modelMapper.map(rentalRepository.save(rental), RentalAgreementResponse.class);
    }

    @Override
    public RentalAgreementResponse refundRental(Long var1) {
        RentalAgreement rental = rentalRepository.findById(var1)
                .orElseThrow(()->new RentalAgreementException("Rent with id "+var1+" is not found"));
        rental.setStatus(RentalStatus.REFUNDED);
        return modelMapper.map(rentalRepository.save(rental), RentalAgreementResponse.class);
    }

    @Override
    public RentalAgreementResponse cancelRental(Long var1) {
        RentalAgreement rental = rentalRepository.findById(var1)
                .orElseThrow(()->new RentalAgreementException("Rent with id "+var1+" is not found"));
        rental.setStatus(RentalStatus.CANCELLED);
        return modelMapper.map(rentalRepository.save(rental), RentalAgreementResponse.class);
    }
}
