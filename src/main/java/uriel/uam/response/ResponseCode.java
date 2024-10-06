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

    // Reservation
    RESERVATION_CREATE_SUCCESSFUL(HttpStatus.OK, "200", "예약 생성 성공");

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
