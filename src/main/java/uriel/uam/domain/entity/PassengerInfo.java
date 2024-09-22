package uriel.uam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_NO")
    Integer id;

    // 승객 이름
    String name;

    // 승객 생년월일
    LocalDate birthday;

    // 연락처
    String tel;

    // 이미지 url
    String imageUrl;

    @OneToMany(fetch = FetchType.LAZY)
    List<Reservation> reservationList;
}
