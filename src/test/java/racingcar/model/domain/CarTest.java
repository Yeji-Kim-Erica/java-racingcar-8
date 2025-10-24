package racingcar.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Car 클래스")
public class CarTest {
    @ParameterizedTest
    @DisplayName("객체 생성 성공 테스트")
    @ValueSource(strings = {"pobi", "woni", "   pobi   "})
    void createCar(String name) {
        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo(name.trim());
    }

    @Nested
    @DisplayName("예외 처리 테스트")
    class ExceptionTest {
        @ParameterizedTest
        @DisplayName("이름이 공백 문자거나 비어있는 경우")
        @ValueSource(strings = {"   ", ""})
        void nameIsNullOrEmpty(String name) {
            assertThatThrownBy(() -> new Car(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CAR_NAME_NULL_OR_EMPTY.getMessage());
        }

        @Test
        @DisplayName("이름의 길이가 최대 기준을 초과한 경우")
        void lengthOfNameExceedsMaximum() {
            assertThatThrownBy(() -> new Car("abcdef"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CAR_NAME_LENGTH_OVER_MAXIMUM.getMessage());
        }
    }
}
