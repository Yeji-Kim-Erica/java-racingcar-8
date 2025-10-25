package racingcar.model.domain;

import racingcar.model.dto.RoundResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 자동차 경주 도메인 클래스
 */
public class Race {
    private final List<Car> cars;
    private final int finalRound;
    private final MoveStrategy moveStrategy;
    private final List<RoundResult> roundResults;

    public Race(RaceConfiguration configuration, MoveStrategy moveStrategy) {
        this.cars = configuration.getCars();
        this.finalRound = configuration.getRounds();
        this.moveStrategy = moveStrategy;
        roundResults = new ArrayList<>();
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public List<RoundResult> getRoundResults() {
        return new ArrayList<>(roundResults);
    }

    public void proceed() {
        for (int i = 1; i <= finalRound; i++) {
            RoundResult roundResult = proceedOneRound();
            roundResults.add(roundResult);
        }
    }

    private RoundResult proceedOneRound() {
        RoundResult roundResult = new RoundResult();
        for (Car car : cars) {
            car.tryMove(moveStrategy);
            roundResult.add(car);
        }
        return roundResult;
    }

    public List<String> findWinner() {
        List<String> winners = new ArrayList<>();
        int maxDistance = findMaxDistance();
        for (Car car : cars) {
            if (car.getPosition() == maxDistance) {
                String name = car.getName();
                winners.add(name);
            }
        }
        return winners;
    }

    private int findMaxDistance() {
        int maxDistance = 0;
        for (Car car : cars) {
            maxDistance = Math.max(maxDistance, car.getPosition());
        }
        return maxDistance;
    }
}
