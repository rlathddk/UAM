package uriel.uam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_NO")
    private Integer id;

    // 항공사 2코드
    private String airlineCode;

    // 항공편명
    private String flightNo;

    // 출발지
    private String departure;

    // 출발 시간
    private LocalDateTime departureTime;

    // 도착지
    private String arrival;

    // 도착 시간
    private LocalDateTime arrivalTime;

    @OneToMany(fetch = FetchType.LAZY)
    List<Reservation> reservationList = new ArrayList<>();
}
