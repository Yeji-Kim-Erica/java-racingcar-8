package racingcar.model.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.dto.RoundResultDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DisplayName("Race 클래스")
public class RaceTest {
    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        private Car pobi;
        private Car woni;
        private List<Car> cars;
        private RaceConfiguration config;
        private MoveStrategy moveStrategy;

        @BeforeEach
        void setUpCars() {
            pobi = new Car("pobi");
            woni = new Car("woni");
            cars = List.of(pobi, woni);
            moveStrategy = new MoveStrategy() {
                @Override
                public boolean isMoveable() {
                    return true;
                }
            };
        }

        @ParameterizedTest
        @DisplayName("한 경기당 시도 횟수만큼의 라운드 진행")
        @ValueSource(ints = {1, 2, 3})
        void raceProceedsForCorrectNumberOfRounds(int rounds) {
            // given
            RaceConfiguration configuration = new RaceConfiguration(cars, rounds);
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

        @Test
        @DisplayName("라운드별 결과 기록")
        void accurateRoundResult() {
            // given
            RaceConfiguration configuration = new RaceConfiguration(cars, 2);
            Race race = new Race(configuration, moveStrategy);
            race.proceed();

            // when
            List<RoundResultDto> results = race.getRoundResults();

            // then
            RoundResultDto round1 = results.getFirst();
            assertThat(round1.getCarPositions()).containsEntry("pobi", 1);
            assertThat(round1.getCarPositions()).containsEntry("woni", 1);

            RoundResultDto round2 = results.getLast();
            assertThat(round2.getCarPositions()).containsEntry("pobi", 2);
            assertThat(round2.getCarPositions()).containsEntry("woni", 2);
        }

        @Test
        @DisplayName("공동 우승자")
        void findMultipleWinners() {
            // given
            RaceConfiguration configuration = new RaceConfiguration(cars, 2);
            Race race = new Race(configuration, moveStrategy);
            race.proceed();

            // when
            List<String> winners = race.findWinner();

            // then
            assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
        }

        @Test
        @DisplayName("단독 우승자")
        void findSingleWinner() {
            // given
            Car singleWinner = new Car("jun", 2);
            Car loser = new Car("loser");
            List<Car> carList = List.of(singleWinner, loser);
            RaceConfiguration configuration = new RaceConfiguration(carList, 2);
            Race race = new Race(configuration, moveStrategy);
            race.proceed();

            // when
            List<String> winners = race.findWinner();

            // then
            assertThat(winners).size().isEqualTo(1);
            assertThat(winners).containsExactly("jun");
        }
    }
}
