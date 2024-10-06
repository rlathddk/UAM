package uriel.uam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_NO")
    private Integer id;

    // 예약번호
    private String pnr;

    // 출발지
    private String departure;

    // 출발시간
    private LocalDateTime departureTime;

    // 도착지
    private String arrival;

    // 도착시간
    private LocalDateTime arrivalTime;

    // 항공사 코드
    private String airlineCode;

    // 항공편명
    private String flightNo;

    @ManyToOne
    FlightSchedule flightSchedule;

    @ManyToOne
    PassengerInfo passengerInfo;
}
