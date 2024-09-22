package uriel.uam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_NO")
    Integer id;

    // 항공사 2코드
    String airlineCode;

    // 항공편명
    String flightNo;

    // 출발지
    String departure;

    // 출발 시간
    LocalDateTime departureTime;

    // 도착지
    String arrival;

    // 도착 시간
    LocalDateTime arrivalTime;

    @OneToMany(fetch = FetchType.LAZY)
    List<Reservation> reservationList;
}
