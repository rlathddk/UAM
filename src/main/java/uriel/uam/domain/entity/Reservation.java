package uriel.uam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    FlightSchedule flightSchedule;

    @ManyToOne
    PassengerInfo passengerInfo;
}
