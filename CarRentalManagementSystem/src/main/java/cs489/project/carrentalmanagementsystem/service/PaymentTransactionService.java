package cs489.project.carrentalmanagementsystem.service;

import cs489.project.carrentalmanagementsystem.dto.payment.request.PaymentTransactionRequest;
import cs489.project.carrentalmanagementsystem.dto.payment.response.PaymentTransactionResponse;

import java.util.List;

public interface PaymentTransactionService {
    PaymentTransactionResponse createPaymentTransaction(PaymentTransactionRequest paymentTransactionRequest);
    PaymentTransactionResponse getPaymentTransaction(Long id);
    PaymentTransactionResponse updatePaymentTransaction(Long id, PaymentTransactionRequest paymentTransactionRequest);
    void deletePaymentTransaction(Long id);

    PaymentTransactionResponse refundPaymentByReservationID(Long id);

    PaymentTransactionResponse updatePaymentPrice(Long id, double amount);

    List<PaymentTransactionResponse> getAllPaymentTransactions();
}
