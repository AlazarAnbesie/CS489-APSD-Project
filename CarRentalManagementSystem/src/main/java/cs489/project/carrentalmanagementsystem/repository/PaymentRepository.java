package cs489.project.carrentalmanagementsystem.repository;

import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentTransaction, Long> {
}
