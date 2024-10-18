package uriel.uam.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uriel.uam.domain.entity.FlightSchedule;
import uriel.uam.domain.entity.PassengerInfo;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponseDto {

    // 예약번호
    private String pnr;

    // 출발지
    private String departure;

    // 출발시간
    private String departureTime;

    // 도착지
    private String arrival;

    // 도착시간
    private String arrivalTime;

    // 항공편명
    private String flightNo;

    // 항공사 코드
    private String airlineCode;

    // 승객 이름
    private String name;

    // 연락처
    private String tel;

    public static ReservationResponseDto of(FlightSchedule flightSchedule, PassengerInfo passengerInfo, String tel, String pnr) {
        return ReservationResponseDto.builder()
                .pnr(pnr)
                .name(passengerInfo.getName())
                .departure(flightSchedule.getDeparture())
                .departureTime(flightSchedule.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .arrival(flightSchedule.getArrival())
                .arrivalTime(flightSchedule.getArrivalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .airlineCode(flightSchedule.getAirlineCode())
                .flightNo(flightSchedule.getFlightNo())
                .tel(tel)
                .build();
    }
}
