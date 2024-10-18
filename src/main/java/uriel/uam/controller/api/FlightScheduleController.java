package uriel.uam.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uriel.uam.domain.dto.response.FlightScheduleResponseDto;
import uriel.uam.domain.entity.FlightSchedule;
import uriel.uam.response.ResResult;
import uriel.uam.response.ResponseCode;
import uriel.uam.service.FlightScheduleService;

import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    @GetMapping("/airline/list")
    public ResponseEntity<ResResult> airlineList() {
        List<String> airlineList = flightScheduleService.airlineList();
        ResponseCode responseCode = ResponseCode.ALL_AIRLINE_CODE_SEARCH_SUCCESS_FULL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(airlineList)
                        .build()
        );
    }

    @GetMapping("/{airlineCode}/departure/list")
    public ResponseEntity<ResResult> departureList(@PathVariable String airlineCode) {
        List<String> departureAirportList = flightScheduleService.departureAirportList(airlineCode);
        ResponseCode responseCode = ResponseCode.DEPARTURE_AIRPORT_SEARCH_SUCCESS_FULL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(departureAirportList)
                        .build()
        );
    }

    @GetMapping("/{airlineCode}/departure/{departureAirportCode}/arrival/list")
    public ResponseEntity<ResResult> arrivalList(@PathVariable String airlineCode, @PathVariable String departureAirportCode) {
        List<String> arrivalAirportList = flightScheduleService.arrivalAirportList(airlineCode, departureAirportCode);
        ResponseCode responseCode = ResponseCode.ARRIVAL_AIRPORT_SEARCH_SUCCESS_FULL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(arrivalAirportList)
                        .build()
        );
    }

    @GetMapping("/{airlineCode}/departure/{departureAirportCode}/arrival/{arrivalAirportCode}/list")
    public ResponseEntity<ResResult> searchFlightList(@PathVariable String airlineCode, @PathVariable String departureAirportCode, @PathVariable String arrivalAirportCode) {
        List<FlightSchedule> flightList = flightScheduleService.searchFlightList(airlineCode, departureAirportCode, arrivalAirportCode);
        ResponseCode responseCode = ResponseCode.FLIGHT_SEARCH_SUCCESS_FULL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(flightList.stream().map(FlightScheduleResponseDto::of))
                        .build()
        );
    }
}
