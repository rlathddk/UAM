package uriel.uam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uriel.uam.domain.dto.request.PassengerInfoRequestDto;
import uriel.uam.domain.dto.response.PassengerInfoResponseDto;
import uriel.uam.domain.entity.PassengerInfo;
import uriel.uam.exception.CustomException;
import uriel.uam.exception.ErrorCode;
import uriel.uam.repository.PassengerInfoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassengerInfoService {

    private final PassengerInfoRepository passengerInfoRepository;

    /**
     * 생성
     */
    @Transactional
    public PassengerInfoResponseDto createPassengerInfo(PassengerInfoRequestDto passengerInfoRequestDto) {

        // imageUrl 이 존재하는지 확인
        Optional<PassengerInfo> byImageUrl = findByImageUrl(passengerInfoRequestDto.getImageUrl());
        if (byImageUrl.isPresent()) {
            // 이름, 생년월일이 같다면 throw 발생
            if (byImageUrl.get().getName().equals(passengerInfoRequestDto.getName()) &&
                    byImageUrl.get().getBirthday().equals(LocalDate.parse(passengerInfoRequestDto.getBirthday(), DateTimeFormatter.ISO_DATE))) {
                throw new CustomException(ErrorCode.PASSENGER_ALREADY_REGISTERED);
            }
        }
        return PassengerInfoResponseDto.of(passengerInfoRepository.save(PassengerInfo.of(passengerInfoRequestDto)));
    }

    /**
     * 승객ID로 조회
     */
    private Optional<PassengerInfo> findByPassengerId(Integer passengerId) {
        return passengerInfoRepository.findById(passengerId);
    }

    /**
     * 승객 imageUrl로 조회
     */
    public Optional<PassengerInfo> findByImageUrl(String imageUrl) {
        return passengerInfoRepository.findByImageUrl(imageUrl);
    }

    /**
     * 승객 ID로 조회 후 반환
     *
     * @param id
     * @return
     */
    public PassengerInfoResponseDto passengerSearch(Integer id) {
        Optional<PassengerInfo> byPassengerId = findByPassengerId(id);
        if (!byPassengerId.isPresent()) {
            throw new CustomException(ErrorCode.PASSENGER_INFO_NOT_FOUND);
        }
        return PassengerInfoResponseDto.of(byPassengerId.get());
    }
}
