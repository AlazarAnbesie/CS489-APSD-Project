package cs489.project.carrentalmanagementsystem.service.impl;

import cs489.project.carrentalmanagementsystem.dto.payment.request.PaymentTransactionRequest;
import cs489.project.carrentalmanagementsystem.dto.payment.response.PaymentTransactionResponse;
import cs489.project.carrentalmanagementsystem.exception.ReservationException;
import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentStatus;
import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import cs489.project.carrentalmanagementsystem.repository.PaymentRepository;
import cs489.project.carrentalmanagementsystem.repository.ReservationRepository;
import cs489.project.carrentalmanagementsystem.service.PaymentTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    private final ModelMapper modelMapper;

    public PaymentTransactionServiceImpl(PaymentRepository paymentRepository, ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PaymentTransactionResponse createPaymentTransaction(PaymentTransactionRequest paymentTransactionRequest) {
        Reservation reservation = reservationRepository.findById(paymentTransactionRequest.getReservationId()).orElseThrow(() -> new ReservationException("Reservation not found"));
        PaymentTransaction paymentTransaction = reservation.getPaymentTransaction();
        paymentTransaction.setPaymentStatus(paymentTransactionRequest.getPaymentStatus());
        paymentTransaction.setPaymentType(paymentTransactionRequest.getPaymentType());
        paymentTransaction.setPaymentDate(LocalDate.now());
        paymentTransaction.setCustomer(reservation.getCustomer());
        PaymentTransaction savedPaymentTransaction = paymentRepository.save(paymentTransaction);
        return modelMapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse getPaymentTransaction(Long id) {
        return modelMapper.map(paymentRepository.findById(id).orElseThrow(() -> new ReservationException("Payment Transaction not found")), PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse updatePaymentTransaction(Long id, PaymentTransactionRequest paymentTransactionRequest) {
        PaymentTransaction paymentTransaction = paymentRepository.findById(id).orElseThrow(() -> new ReservationException("Payment Transaction not found"));
        paymentTransaction.setPaymentType(paymentTransactionRequest.getPaymentType());
        paymentTransaction.setPaymentStatus(paymentTransactionRequest.getPaymentStatus());
        paymentTransaction.setPaymentDate(LocalDate.now());
        PaymentTransaction savedPaymentTransaction = paymentRepository.save(paymentTransaction);
        return modelMapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public void deletePaymentTransaction(Long id) {
        paymentRepository.findById(id).orElseThrow(()-> new ReservationException("Payment Transaction not found"));
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentTransactionResponse refundPaymentByReservationID(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationException("Reservation not found"));
        PaymentTransaction paymentTransaction = reservation.getPaymentTransaction();
        paymentTransaction.setPaymentStatus(PaymentStatus.REFUNDED);
        PaymentTransaction savedPaymentTransaction = paymentRepository.save(paymentTransaction);
        return modelMapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public PaymentTransactionResponse updatePaymentPrice(Long id, double amount) {
        PaymentTransaction paymentTransaction = paymentRepository.findById(id).orElseThrow(() -> new ReservationException("Payment Transaction not found"));
        paymentTransaction.setAmount(amount);
        PaymentTransaction savedPaymentTransaction = paymentRepository.save(paymentTransaction);
        return modelMapper.map(savedPaymentTransaction, PaymentTransactionResponse.class);
    }

    @Override
    public List<PaymentTransactionResponse> getAllPaymentTransactions() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentTransaction -> modelMapper
                        .map(paymentTransaction, PaymentTransactionResponse.class))
                .toList();
    }
}
