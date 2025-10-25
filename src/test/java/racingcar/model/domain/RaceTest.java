package racingcar.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DisplayName("Race 클래스")
public class RaceTest {
    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @ParameterizedTest
        @DisplayName("한 경기당 시도 횟수만큼의 라운드 진행")
        @ValueSource(ints = {1, 2, 3})
        void proceedRace(int rounds) {
            // given
            List<Car> validCarList = List.of(new Car("pobi"), new Car("woni"));
            RaceConfiguration configuration = new RaceConfiguration(validCarList, rounds);
            MoveStrategy moveStrategy = new MoveStrategy() {
                @Override
                public boolean isMoveable() {
                    return true;
                }
            };
            Race race = new Race(configuration, moveStrategy);

            // when
            race.proceed();

            // then
            assertThat(race.getCars())
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni");
            assertThat(race.getCars())
                    .extracting(Car::getPosition)
                    .containsExactly(rounds, rounds);
        }
    }
}
