package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.Reservation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByPnr(String pnr);
}
