package cs489.project.carrentalmanagementsystem.repository;

import cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<RentalAgreement, Long> {
}
