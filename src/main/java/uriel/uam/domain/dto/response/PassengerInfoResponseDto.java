package uriel.uam.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uriel.uam.domain.entity.PassengerInfo;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassengerInfoResponseDto {

    // 승객 이름
    String name;

    // 승객 생년월일
    LocalDate birthday;

    // 연락처
    String tel;

    // 이미지 url
    String imageUrl;

    public static PassengerInfoResponseDto of(PassengerInfo passengerInfo) {
        return PassengerInfoResponseDto.builder()
                .name(passengerInfo.getName())
                .birthday(passengerInfo.getBirthday())
                .tel(passengerInfo.getTel())
                .imageUrl(passengerInfo.getImageUrl())
                .build();
    }
}
