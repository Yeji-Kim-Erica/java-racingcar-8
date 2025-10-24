package racingcar.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DisplayName("RaceConfiguration 클래스")
public class RaceConfigurationTest {
    private static final List<Car> VALID_CARS = List.of(new Car("pobi"), new Car("woni"));
    private static final int VALID_ROUNDS = 3;

    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @Test
        @DisplayName("유효한 값으로 경기 설정 객체 생성")
        void createRaceConfiguration() {
            // when
            RaceConfiguration configuration = new RaceConfiguration(VALID_CARS, VALID_ROUNDS);

            // then
            assertThat(configuration.getCars()).containsExactlyElementsOf(VALID_CARS);
            assertThat(configuration.getRounds()).isEqualTo(VALID_ROUNDS);
        }
    }

    @Nested
    @DisplayName("예외 처리 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("경기할 자동차의 수가 최소 기준 미달인 경우")
        void numberOfCarLessThanMinimum() {
            // given
            List<Car> cars = List.of(new Car("car1"));

            // when & then
            assertThatThrownBy(() -> new RaceConfiguration(cars, VALID_ROUNDS))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CARS_LESS_THAN_MINIMUM.getMessage());
        }
        
        @Test
        @DisplayName("자동차 이름이 중복되는 경우")
        void duplicatedCarName() {
            // given
            List<Car> cars = List.of(new Car("car"), new Car("car"), new Car("car2"));

            // when & then
            assertThatThrownBy(() -> new RaceConfiguration(cars, VALID_ROUNDS))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.DUPLICATED_CAR_NAME.getMessage());
        }

        @ParameterizedTest
        @DisplayName("시도 횟수가 양수가 아닌 경우")
        @ValueSource(ints = {0, -1})
        void roundsNotPositive(int rounds) {
            assertThatThrownBy(() -> new RaceConfiguration(VALID_CARS, rounds))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.ROUNDS_NOT_POSITIVE.getMessage());
        }
    }

}
