package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.PassengerInfo;

import java.util.Optional;

public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Integer> {
    Optional<PassengerInfo> findByImageUrl(String imageUrl);

    Optional<PassengerInfo> findByIdAndImageUrl(Integer passengerId, String imageUrl);
}
