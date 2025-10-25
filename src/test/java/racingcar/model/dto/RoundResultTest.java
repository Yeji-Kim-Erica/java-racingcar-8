package racingcar.model.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.model.domain.Car;
import racingcar.model.domain.MoveStrategy;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RoundResult 클래스")
public class RoundResultTest {
    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @Test
        @DisplayName("자동차 위치 저장")
        void saveCarPositionInRightOrder() {
            // when
            RoundResult roundResult = new RoundResult();
            roundResult.add(new Car("pobi", 2));
            roundResult.add(new Car("woni", 4));
            roundResult.add(new Car("jun", 1));
            Map<String, Integer> result = roundResult.getCarPositions();

            // then
            assertThat(result.keySet())
                    .containsExactly("pobi", "woni", "jun");
        }

        @Test
        @DisplayName("자동차 위치가 저장된 시점의 위치로 유지됨")
        void carPositionNotChangeable() {
            // given
            MoveStrategy moveStrategy = new MoveStrategy() {
                @Override
                public boolean isMoveable() {
                    return true;
                }
            };
            Car car1 = new Car("pobi", 2);
            Car car2 = new Car("woni", 4);
            Car car3 = new Car("jun", 1);
            List<Car> cars = List.of(car1, car2, car3);
            RoundResult roundResult = new RoundResult(cars);
            Map<String, Integer> result = roundResult.getCarPositions();

            // when
            car1.tryMove(moveStrategy);
            car2.tryMove(moveStrategy);

            // then
            assertThat(result.values())
                    .containsExactly(2, 4, 1);
        }
    }
}
