package cs489.project.carrentalmanagementsystem.repository;

import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
