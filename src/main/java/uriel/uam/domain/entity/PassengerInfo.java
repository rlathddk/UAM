package uriel.uam.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uriel.uam.domain.dto.request.PassengerInfoRequestDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_NO")
    private Integer id;

    // 승객 이름
    @NotBlank
    private String name;

    // 승객 생년월일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    // 연락처
    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String tel;

    // 이미지 url
//    @NotBlank
    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY)
    List<Reservation> reservationList;

    public static PassengerInfo of(PassengerInfoRequestDto passengerInfoRequestDto) {
        return PassengerInfo.builder().name(passengerInfoRequestDto.getName()).birthday(LocalDate.parse(passengerInfoRequestDto.getBirthday(), DateTimeFormatter.ISO_DATE)).tel(passengerInfoRequestDto.getTel()).imageUrl(passengerInfoRequestDto.getImageUrl()).build();
    }
}
