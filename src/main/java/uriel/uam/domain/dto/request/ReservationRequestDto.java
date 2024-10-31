package uriel.uam.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class  ReservationRequestDto {

    // 승객 ID
    private Integer passengerId;

    // 항공사 코드
    private String airlineCode;

    // 출발지
    private String departure;

    // 출발시간
    private String departureTime;

    // 도착지
    private String arrival;

    // 연락처
    private String tel;
}
