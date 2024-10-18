package uriel.uam.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public enum ResponseCode {
    // Member
    PASSENGER_INFO_GET_SUCCESSFUL(HttpStatus.OK, "200", "승객 정보 조회 성공"),
    PASSENGER_INFO_CREATE_SUCCESSFUL(HttpStatus.OK, "200", "승객 생성 성공"),

    // faceScan
    FACE_SCAN_SUCCESSFUL(HttpStatus.OK, "200", "얼굴 스캔 성공"),

    // flight
    ALL_AIRLINE_CODE_SEARCH_SUCCESS_FULL(HttpStatus.OK, "200", "모든 항공사 리스트 조회 성공"),
    DEPARTURE_AIRPORT_SEARCH_SUCCESS_FULL(HttpStatus.OK, "200", "출발지 리스트 조회 성공"),
    ARRIVAL_AIRPORT_SEARCH_SUCCESS_FULL(HttpStatus.OK, "200", "도착지 리스트 조회 성공"),
    FLIGHT_SEARCH_SUCCESS_FULL(HttpStatus.OK, "200", "조건에 맞는 항공편 조회 성공"),

    // Reservation,
    RESERVATION_CREATE_SUCCESSFUL(HttpStatus.OK, "200", "예약 생성 성공"),
    PASSENGER_RESERVATION_RETRIEVAL_SUCCESSFUL(HttpStatus.OK, "200", "승객 예약 리스트 조회 완료"),
    PASSENGER_RESERVATION_DETAIL_RETRIEVAL_SUCCESSFUL(HttpStatus.OK, "200", "승객 예약 상세 조회 성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public ResponseEntity<ResResult> toResponse(Object data) {
        return new ResponseEntity<>(ResResult.builder()
                .responseCode(this)
                .code(this.code)
                .message(this.message)
                .data(data)
                .build(), httpStatus.OK);
    }
}
