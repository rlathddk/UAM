package uriel.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uriel.uam.domain.entity.PassengerInfo;

public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Integer> {
}
