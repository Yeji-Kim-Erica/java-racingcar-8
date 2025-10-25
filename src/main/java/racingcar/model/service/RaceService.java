package racingcar.model.service;

import racingcar.model.domain.*;
import racingcar.model.dto.RoundResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 경기 실행 관련 로직을 담당하는 클래스
 */
public class RaceService {
    private final MoveStrategy moveStrategy;

    public RaceService(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public Race proceedRace(RaceConfiguration configuration) {
        Race race = new Race(configuration, moveStrategy);
        race.proceed();

        return race;
    }

    public List<RoundResult> getRaceResult(Race race) {
        return race.getRoundResults();
    }
}
