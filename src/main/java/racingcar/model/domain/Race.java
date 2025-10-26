package racingcar.model.domain;

import racingcar.model.dto.RoundResultDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 자동차 경주 도메인 클래스
 */
public class Race {
    private final List<Car> cars;
    private final int finalRound;
    private final MoveStrategy moveStrategy;
    private final List<RoundResultDto> roundResults;

    public Race(RaceConfiguration configuration, MoveStrategy moveStrategy) {
        this.cars = configuration.getCars();
        this.finalRound = configuration.getRounds();
        this.moveStrategy = moveStrategy;
        this.roundResults = new ArrayList<>();
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public List<RoundResultDto> getRoundResults() {
        return new ArrayList<>(roundResults);
    }

    public void proceed() {
        for (int i = 1; i <= finalRound; i++) {
            RoundResultDto roundResultDto = proceedOneRound();
            roundResults.add(roundResultDto);
        }
    }

    private RoundResultDto proceedOneRound() {
        RoundResultDto roundResultDto = new RoundResultDto();
        for (Car car : cars) {
            car.move(moveStrategy.isMoveable());
            roundResultDto.add(car);
        }
        return roundResultDto;
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
