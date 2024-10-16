package uriel.uam.base;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import uriel.uam.domain.entity.FlightSchedule;
import uriel.uam.domain.entity.PassengerInfo;
import uriel.uam.repository.FlightScheduleRepository;
import uriel.uam.repository.PassengerInfoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
public class InitData {

    @Bean
    CommandLineRunner init(PassengerInfoRepository passengerInfoRepository, FlightScheduleRepository flightScheduleRepository) {

        return args -> {
            if (flightScheduleRepository.count() == 0 && !flightScheduleRepository.existsById(1)) {
                for (int i = 0; i < 7; i++) {
                    List<FlightSchedule> flightScheduleList = new ArrayList<>();
                    flightScheduleList.addAll(List.of(FlightSchedule.builder()
                                    .airlineCode("UR")
                                    .flightNo("UR1001")
                                    .departure("INCHON")
                                    .departureTime(LocalDate.now().atTime(14, 30).plusDays(i))
                                    .arrival("GIMPO")
                                    .arrivalTime(LocalDate.now().atTime(14, 40).plusDays(i))
                                    .build(),

                            FlightSchedule.builder()
                                    .airlineCode("UR")
                                    .flightNo("UR1002")
                                    .departure("GIMPO")
                                    .departureTime(LocalDate.now().atTime(15, 30).plusDays(i))
                                    .arrival("INCHON")
                                    .arrivalTime(LocalDate.now().atTime(15, 40).plusDays(i))
                                    .build(),

                            FlightSchedule.builder()
                                    .airlineCode("UR")
                                    .flightNo("UR1011")
                                    .departure("GANGNAM")
                                    .departureTime(LocalDate.now().atTime(12, 30).plusDays(i))
                                    .arrival("YONGIN")
                                    .arrivalTime(LocalDate.now().atTime(12, 40).plusDays(i))
                                    .build(),

                            FlightSchedule.builder()
                                    .airlineCode("UR")
                                    .flightNo("UR1012")
                                    .departure("YONGIN")
                                    .departureTime(LocalDate.now().atTime(13, 30).plusDays(i))
                                    .arrival("GANGNAM")
                                    .arrivalTime(LocalDate.now().atTime(13, 40).plusDays(i))
                                    .build()));

                    flightScheduleRepository.saveAll(flightScheduleList);
                }
            }

            if (passengerInfoRepository.count() == 0 && !passengerInfoRepository.existsById(1)) {
                PassengerInfo test1 = PassengerInfo.builder()
                        .name("kim.test")
                        .birthday(LocalDate.parse("1999-01-01", DateTimeFormatter.ISO_DATE))
                        .tel("010-1234-1234")
                        .imageUrl("com.test1")
                        .build();
                passengerInfoRepository.save(test1);

                PassengerInfo test2 = PassengerInfo.builder()
                        .name("jung.test")
                        .birthday(LocalDate.parse("1999-02-01", DateTimeFormatter.ISO_DATE))
                        .tel("010-5678-5678")
                        .imageUrl("com.test2")
                        .build();
                passengerInfoRepository.save(test2);
            }
        };
    }
}
