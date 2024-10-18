package uriel.uam.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uriel.uam.domain.dto.request.ReservationRequestDto;
import uriel.uam.domain.dto.response.ReservationResponseDto;
import uriel.uam.response.ResResult;
import uriel.uam.response.ResponseCode;
import uriel.uam.service.ReservationService;

import java.util.List;

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

    @GetMapping("/passenger/{passengerId}/reservation/list")
    public ResponseEntity<ResResult> paxReservationList(@PathVariable Integer passengerId) {
        List<ReservationResponseDto> paxReservationList = reservationService.paxReservationList(passengerId);
        ResponseCode responseCode = ResponseCode.PASSENGER_RESERVATION_RETRIEVAL_SUCCESSFUL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(paxReservationList)
                        .build()
        );
    }

    @GetMapping("/passenger/{passengerId}/reservation/{reservationId}")
    public ResponseEntity<ResResult> paxReservationDetail(@PathVariable Integer passengerId, @PathVariable Integer reservationId) {
        ReservationResponseDto paxReservationDetail = reservationService.paxReservationDetail(passengerId, reservationId);
        ResponseCode responseCode = ResponseCode.PASSENGER_RESERVATION_DETAIL_RETRIEVAL_SUCCESSFUL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(paxReservationDetail)
                        .build()
        );
    }
}
