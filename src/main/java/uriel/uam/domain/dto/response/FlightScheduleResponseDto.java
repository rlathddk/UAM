package uriel.uam.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uriel.uam.domain.entity.FlightSchedule;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightScheduleResponseDto {

    private Integer id;

    // 항공사 2코드
    private String airlineCode;

    // 항공편명
    private String flightNo;

    // 출발지
    private String departure;

    // 출발 시간
    private String departureTime;

    // 도착지
    private String arrival;

    // 도착 시간
    private String arrivalTime;

    public static FlightScheduleResponseDto of(FlightSchedule flightSchedule) {
        return FlightScheduleResponseDto.builder()
                .id(flightSchedule.getId())
                .airlineCode(flightSchedule.getAirlineCode())
                .flightNo(flightSchedule.getFlightNo())
                .departure(flightSchedule.getDeparture())
                .arrival(flightSchedule.getArrival())
                .departureTime(flightSchedule.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .arrivalTime(flightSchedule.getArrivalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
