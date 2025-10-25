package racingcar.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Car 클래스")
public class CarTest {
    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @ParameterizedTest
        @DisplayName("유효한 값으로 Car 객체 생성")
        @ValueSource(strings = {"pobi", "woni", "   pobi   "})
        void createCar(String name) {
            // when
            Car car = new Car(name);

            // then
            assertThat(car.getName()).isEqualTo(name.trim());
        }
        
        @Test
        @DisplayName("자동차 전진")
        void carMovesForward() {
            // given
            Car car = new Car("pobi");
            MoveStrategy moveStrategy = new MoveStrategy() {
                @Override
                public boolean isMoveable() {
                    return true;
                }
            };

            // when
            car.tryMove(moveStrategy);

            // then
            assertThat(car.getPosition()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("예외 처리 테스트")
    class ExceptionTest {
        @ParameterizedTest
        @DisplayName("이름이 null이거나, 비어있거나, 공백 문자로만 이루어져 있는 경우")
        @NullSource
        @EmptySource
        @ValueSource(strings = {"   ", "  "})
        void nameIsBlank(String name) {
            assertThatThrownBy(() -> new Car(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CAR_NAME_NULL_OR_BLANK.getMessage());
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
