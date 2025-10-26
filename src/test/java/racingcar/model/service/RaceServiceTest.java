package racingcar.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.model.domain.*;
import racingcar.model.dto.RaceResultDto;
import racingcar.model.dto.RoundResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RaceService 클래스")
public class RaceServiceTest {
    private ConfigService configService;
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        configService = new ConfigService();
        MoveStrategy moveStrategy = new MoveStrategy() {
            @Override
            public boolean isMoveable() {
                return true;
            }
        };
        raceService = new RaceService(moveStrategy);
    }

    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @Test
        @DisplayName("유효한 설정값으로 경기 진행")
        void proceedRaceWithValidConfig() {
            // given
            String validCarNames = "pobi,woni";
            String validRounds = "3";
            RaceConfiguration configuration = configService.createRaceConfiguration(validCarNames, validRounds);

            // when
            RaceResultDto raceResult = raceService.runRace(configuration);
            List<RoundResultDto> roundResultList = raceResult.getRoundResultList();

            // then
            assertThat(roundResultList).hasSize(3);

            RoundResultDto lastRoundResult = roundResultList.getLast();
            assertThat(lastRoundResult.getCarPositions()).containsEntry("pobi", 3);
            assertThat(lastRoundResult.getCarPositions()).containsEntry("woni", 3);

            assertThat(raceResult.getWinners()).containsExactlyInAnyOrder("pobi", "woni");
        }
    }
}
