package racingcar.model.service;

import racingcar.model.domain.*;
import racingcar.model.dto.RaceResultDto;
import racingcar.model.dto.RoundResultDto;

import java.util.List;

/**
 * 경기 실행 관련 로직을 담당하는 클래스
 */
public class RaceService {
    private final MoveStrategy moveStrategy;

    public RaceService(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public RaceResultDto runRace(RaceConfiguration configuration) {
        Race race = new Race(configuration, moveStrategy);
        race.proceed();

        List<RoundResultDto> roundResultDtoList = race.getRoundResults();
        List<String> winnerList = race.findWinner();
        return new RaceResultDto(roundResultDtoList, winnerList);
    }
}
