package cs489.project.carrentalmanagementsystem.service;

import cs489.project.carrentalmanagementsystem.dto.rent.request.RentalAgreementRequest;
import cs489.project.carrentalmanagementsystem.dto.rent.response.RentalAgreementResponse;

import java.util.List;

public interface RentalAgreementService {
    List<RentalAgreementResponse> getAllRentals();

    RentalAgreementResponse getRentalById(Long var1);

    RentalAgreementResponse createRental(RentalAgreementRequest var1);

    RentalAgreementResponse updateRental(RentalAgreementRequest var1, Long var2);


    RentalAgreementResponse refundRental(Long var1);

    RentalAgreementResponse cancelRental(Long var1);
}
