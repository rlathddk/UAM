package uriel.uam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uriel.uam.domain.dto.request.PassengerInfoRequestDto;
import uriel.uam.domain.dto.response.PassengerInfoResponseDto;
import uriel.uam.response.ResResult;
import uriel.uam.response.ResponseCode;
import uriel.uam.service.PassengerInfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PassengerInfoController {

    private final PassengerInfoService passengerInfoService;

    @PostMapping("/create")
    public ResponseEntity<ResResult> signup(@RequestBody PassengerInfoRequestDto passengerInfoRequestDto) {
        PassengerInfoResponseDto passengerInfoResponseDto = passengerInfoService.createPassengerInfo(passengerInfoRequestDto);
        ResponseCode responseCode = ResponseCode.PASSENGER_INFO_CREATE_SUCCESSFUL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(passengerInfoResponseDto)
                        .build()
        );
    }

}
