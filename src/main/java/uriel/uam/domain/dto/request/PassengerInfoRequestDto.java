package uriel.uam.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PassengerInfoRequestDto {

    // 승객 이름
    String name;

    // 승객 생년월일
    String birthday;

    // 연락처
    String tel;

    // 이미지 url
    String imageUrl;
}
