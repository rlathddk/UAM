package uriel.uam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uriel.uam.domain.dto.request.ReservationRequestDto;
import uriel.uam.domain.dto.response.ReservationResponseDto;
import uriel.uam.response.ResResult;
import uriel.uam.response.ResponseCode;
import uriel.uam.service.ReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ResResult> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        ReservationResponseDto reservationResponseDto = reservationService.createReservationInfo(reservationRequestDto);
        ResponseCode responseCode = ResponseCode.RESERVATION_CREATE_SUCCESSFUL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(reservationResponseDto)
                        .build()
        );
    }

}
