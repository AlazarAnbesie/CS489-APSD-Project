package cs489.project.carrentalmanagementsystem.service.impl;

import cs489.project.carrentalmanagementsystem.dto.reservation.request.ReservationRequest;
import cs489.project.carrentalmanagementsystem.dto.reservation.response.ReservationResponse;
import cs489.project.carrentalmanagementsystem.exception.ReservationException;
import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentStatus;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentType;
import cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement;
import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import cs489.project.carrentalmanagementsystem.model.user.Customer;
import cs489.project.carrentalmanagementsystem.model.user.Role;
import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import cs489.project.carrentalmanagementsystem.repository.ReservationRepository;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import cs489.project.carrentalmanagementsystem.repository.VehicleRepository;
import cs489.project.carrentalmanagementsystem.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    //this implementation is used to create a reservation without customer registration pre-hand
    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservationEntity = modelMapper.map(reservationRequest, Reservation.class);
        // if the customer is not registered, create a new customer entity
        Customer customer = (Customer) userRepository.findUserByEmail(reservationRequest.getCustomer().getEmail())
                .orElse(modelMapper.map(reservationRequest.getCustomer(), Customer.class));
        customer.setRoles(List.of(new Role(null, RoleType.CUSTOMER, null)));
        reservationEntity.setCustomer(customer);
        // get the vehicle entity from the database
        Vehicle vehicle = vehicleRepository.findById(reservationRequest.getVehicleId())
                .orElseThrow(() -> new ReservationException("Vehicle with id " + reservationRequest.getVehicleId() + " not found"));
        reservationEntity.setVehicle(vehicle);
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setPaymentStatus(PaymentStatus.PAID);
        double totalPrice = calculateTotalPrice(reservationEntity.getVehicle().getRentalRate(), reservationEntity.getStartDate(), reservationEntity.getEndDate());
        paymentTransaction.setAmount(totalPrice);
        reservationEntity.setTotalPrice(totalPrice);
        paymentTransaction.setPaymentDate(LocalDate.now());
        paymentTransaction.setPaymentType(reservationRequest.getPaymentType());
        paymentTransaction.setCustomer(customer);
        reservationEntity.getVehicle().setVehicleStatus(VehicleStatus.BOOKED);
        reservationEntity.setPaymentTransaction(paymentTransaction);
//        RentalAgreement rentalAgreement = new RentalAgreement();
//        rentalAgreement.setReservation(reservationEntity);
        Reservation savedReservation = reservationRepository.save(reservationEntity);
        return modelMapper.map(savedReservation, ReservationResponse.class);
    }
    // implementation of total price calculation including tax amount
    private double calculateTotalPrice(double rentalRate, LocalDate startDate, LocalDate endDate) {
        long days = startDate.until(endDate).getDays();
        double totalPrice = rentalRate * days;
        double taxAmount = totalPrice * 0.15;
        return totalPrice + taxAmount;
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(()-> new ReservationException("Reservation with id "+ id +" not found"));
        return modelMapper.map(reservation, ReservationResponse.class);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> modelMapper
                        .map(reservation, ReservationResponse.class))
                .toList();
    }

    @Override
    public ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest) {
        Reservation reservationEntity = reservationRepository.findById(id).orElseThrow(()-> new ReservationException("Reservation with id "+ id +" not found"));
        reservationEntity.setReservationNumber(reservationRequest.getReservationNumber());
        reservationEntity.setStartDate(reservationRequest.getStartDate());
        reservationEntity.setEndDate(reservationRequest.getEndDate());
        reservationEntity.setPickupAddress(reservationRequest.getPickupAddress());
        reservationEntity.setDropoffAddress(reservationRequest.getDropoffAddress());
        reservationEntity.setCustomer(modelMapper.map(reservationRequest.getCustomer(), Customer.class));
        return modelMapper.map(reservationEntity, ReservationResponse.class);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.findById(id).ifPresent(reservationRepository::delete);
    }

    @Override
    public ReservationResponse extendReservation(Long id, LocalDate newEndDate) {
        Reservation reservationEntity = reservationRepository.findById(id).orElseThrow(()-> new ReservationException("Reservation with id "+ id +" not found"));
        reservationEntity.setEndDate(newEndDate);
        double totalPrice = calculateTotalPrice(reservationEntity.getVehicle().getRentalRate(), reservationEntity.getStartDate(), reservationEntity.getEndDate());
        double net = totalPrice-reservationEntity.getTotalPrice();
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setPaymentStatus(PaymentStatus.PAID);
        paymentTransaction.setAmount(net);
        paymentTransaction.setPaymentDate(LocalDate.now());
        paymentTransaction.setPaymentType(PaymentType.DEBIT_CARD);
        paymentTransaction.setCustomer(reservationEntity.getCustomer());
        reservationEntity.setTotalPrice(totalPrice);
        Reservation reservation = reservationRepository.save(reservationEntity);
        return modelMapper.map(reservation, ReservationResponse.class);
    }
}
