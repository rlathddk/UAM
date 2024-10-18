package uriel.uam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uriel.uam.domain.entity.FlightSchedule;
import uriel.uam.exception.CustomException;
import uriel.uam.exception.ErrorCode;
import uriel.uam.repository.FlightScheduleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;

    /**
     * 항공사 2code 로 모든 flight 목록 반환
     */
    public List<FlightSchedule> flightList(String airline2Code) {
        Optional<List<FlightSchedule>> byAirlineCode = flightScheduleRepository.findByAirlineCode(airline2Code);
        if (!byAirlineCode.isPresent()) {
            throw new CustomException(ErrorCode.FLIGHT_NOT_FOUND);
        }
        return byAirlineCode.get();
    }

    /**
     * 출발지 공항 조회
     */
    public List<String> departureAirportList(String airline2Code) {
        List<FlightSchedule> flightSchedules = flightList(airline2Code);
        List<String> departureAirportList = flightSchedules.stream().map(flightSchedule -> flightSchedule.getDeparture()).distinct().collect(Collectors.toList());
        return departureAirportList;
    }

    /**
     * 도착지 공항 조회
     */
    public List<String> arrivalAirportList(String airline2Code, String departureAirport) {
        Optional<List<FlightSchedule>> byAirlineCodeAndDepartureCode = flightScheduleRepository.findByAirlineCodeAndDeparture(airline2Code, departureAirport);
        if (!byAirlineCodeAndDepartureCode.isPresent()) {
            throw new CustomException(ErrorCode.FLIGHT_NOT_FOUND);
        }
        List<String> departureAirportList = byAirlineCodeAndDepartureCode.get().stream().map(flightSchedule -> flightSchedule.getArrival()).distinct().collect(Collectors.toList());
        return departureAirportList;
    }

    /**
     * 모든 항공사 반환
     *
     * @return
     */
    public List<String> airlineList() {
        return flightScheduleRepository.findAll().stream().map(flightSchedule -> flightSchedule.getAirlineCode()).distinct().collect(Collectors.toList());
    }

    /**
     * 출발지, 도착지를 이용한 항공편 리스트 반환
     */
    public List<FlightSchedule> searchFlightList(String airline2Code, String departureAirport, String arrivalAirport) {
        Optional<List<FlightSchedule>> byAirlineCodeAndDepartureAndArrival = flightScheduleRepository.findByAirlineCodeAndDepartureAndArrival(airline2Code, departureAirport, arrivalAirport);
        if (!byAirlineCodeAndDepartureAndArrival.isPresent()) {
            throw new CustomException(ErrorCode.FLIGHT_NOT_FOUND);
        }
        return byAirlineCodeAndDepartureAndArrival.get();
    }
}
