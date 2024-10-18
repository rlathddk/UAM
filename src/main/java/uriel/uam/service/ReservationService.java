package uriel.uam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uriel.uam.domain.dto.request.ReservationRequestDto;
import uriel.uam.domain.dto.response.ReservationResponseDto;
import uriel.uam.domain.entity.FlightSchedule;
import uriel.uam.domain.entity.PassengerInfo;
import uriel.uam.domain.entity.Reservation;
import uriel.uam.exception.CustomException;
import uriel.uam.exception.ErrorCode;
import uriel.uam.repository.FlightScheduleRepository;
import uriel.uam.repository.PassengerInfoRepository;
import uriel.uam.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PassengerInfoRepository passengerInfoRepository;
    private final FlightScheduleRepository flightScheduleRepository;

    /**
     * 생성
     */
    @Transactional
    public ReservationResponseDto createReservationInfo(ReservationRequestDto reservationRequestDto) {

        // 생성 요청한 승객
        PassengerInfo passengerInfo = passengerInfoRepository.findById(reservationRequestDto.getPassengerId()).orElseThrow(() ->
                new CustomException(ErrorCode.PASSENGER_INFO_NOT_FOUND));

        // 요청에 맞는 flight schedule 찾기
        String airlineCode = reservationRequestDto.getAirlineCode();
        String departure = reservationRequestDto.getDeparture();
        LocalDateTime departureTime = localDateTimeFormat(reservationRequestDto.getDepartureTime());
        String arrival = reservationRequestDto.getArrival();
        FlightSchedule flightSchedule = flightScheduleRepository.findByAirlineCodeAndDepartureAndDepartureTimeAndArrival(airlineCode, departure, departureTime, arrival)
                .orElseThrow(() -> new CustomException(ErrorCode.FLIGHT_NOT_FOUND));

        // pnr 생성
        String pnr = createPnr();

        // 예약 정보 저장
        Reservation reservation = Reservation.builder()
                .pnr(pnr)
                .departure(flightSchedule.getDeparture())
                .departureTime(flightSchedule.getDepartureTime())
                .arrival(flightSchedule.getArrival())
                .arrivalTime(flightSchedule.getArrivalTime())
                .flightNo(flightSchedule.getFlightNo())
                .airlineCode(flightSchedule.getAirlineCode())
                .flightSchedule(flightSchedule)
                .passengerInfo(passengerInfo)
                .build();
        reservationRepository.save(reservation);

        flightSchedule.getReservationList().add(reservation);
        flightScheduleRepository.save(flightSchedule);

        passengerInfo.getReservationList().add(reservation);
        passengerInfoRepository.save(passengerInfo);

        return ReservationResponseDto.of(flightSchedule, passengerInfo, reservationRequestDto.getTel(), pnr);
    }

    /**
     * pnr 생성
     */
    public String createPnr() {
        for (; ; ) {
            StringBuilder sb = new StringBuilder();
            Random rd = new Random();
            for (int i = 0; i < 6; i++) {
                if (rd.nextBoolean()) {
                    sb.append(rd.nextInt(10));
                } else {
                    sb.append((char) (rd.nextInt(26) + 65));
                }
            }
            if (!reservationRepository.findByPnr(sb.toString()).isPresent()) {
                return sb.toString();
            }
        }
    }

    /**
     * 요청 날짜 format
     */
    public LocalDateTime localDateTimeFormat(String targetString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(targetString, formatter);
    }
}
