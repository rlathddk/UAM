package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.FlightSchedule;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {
    Optional<FlightSchedule> findByAirlineCodeAndDepartureAndDepartureTimeAndArrivalAndArrivalTime(String airlineCode,
                                                                                                   String departure,
                                                                                                   LocalDateTime departureTime,
                                                                                                   String arrival,
                                                                                                   LocalDateTime arrivalTime);
}
