package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.FlightSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {
    Optional<List<FlightSchedule>> findByAirlineCode(String airline2Code);

    Optional<List<FlightSchedule>> findByAirlineCodeAndDeparture(String airline2Code, String departure);

    Optional<List<FlightSchedule>> findByAirlineCodeAndDepartureAndArrival(String airline2Code, String departureAirport, String arrivalAirport);

    Optional<FlightSchedule> findByAirlineCodeAndDepartureAndDepartureTimeAndArrival(String airlineCode, String departure, LocalDateTime departureTime, String arrival);
}
