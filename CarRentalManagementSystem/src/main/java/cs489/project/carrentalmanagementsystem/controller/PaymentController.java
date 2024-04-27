package cs489.project.carrentalmanagementsystem.controller;

import cs489.project.carrentalmanagementsystem.dto.payment.request.PaymentTransactionRequest;
import cs489.project.carrentalmanagementsystem.service.PaymentTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentTransactionService paymentTransactionService;

    public PaymentController(PaymentTransactionService paymentTransactionService) {
        this.paymentTransactionService = paymentTransactionService;
    }
    //event
    @PostMapping("/pay")
    public ResponseEntity<?> processPayment(@RequestBody PaymentTransactionRequest paymentTransactionRequest) {
        // third party payment gateway integration
        //assume payment is successful
        return ResponseEntity.ok(paymentTransactionService.createPaymentTransaction(paymentTransactionRequest));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> listPayments() {
        return ResponseEntity.ok(paymentTransactionService.getAllPaymentTransactions());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentTransactionService.getPaymentTransaction(id));
    }

    @PostMapping(value="/{id}/cancel")
    public ResponseEntity<?> cancelPaymentById(@PathVariable Long id) {
        paymentTransactionService.deletePaymentTransaction(id);
        return ResponseEntity.ok("Payment cancelled successfully");
    }

    @PostMapping(value="/{id}/refund")
    public ResponseEntity<?> refundPaymentByReservationId(@PathVariable Long id) {
        return ResponseEntity.ok(paymentTransactionService.refundPaymentByReservationID(id));
    }

    @PutMapping(value="/{id}/update")
    public ResponseEntity<?> updatePaymentById(PaymentTransactionRequest payment, @PathVariable Long id) {
        return ResponseEntity.ok(paymentTransactionService.updatePaymentTransaction(id, payment));
    }
    @PutMapping(value="/{id}/update/price/{amount}")
    public ResponseEntity<?> updatePaymentPriceById(@PathVariable Long id, @PathVariable double amount) {
        return ResponseEntity.ok(paymentTransactionService.updatePaymentPrice(id, amount));
    }

}
