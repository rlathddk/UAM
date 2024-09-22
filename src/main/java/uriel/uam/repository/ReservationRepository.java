package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
