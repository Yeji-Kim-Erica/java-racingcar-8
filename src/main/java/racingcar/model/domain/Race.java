package racingcar.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 자동차 경주 도메인 클래스
 */
public class Race {
    private final List<Car> cars;
    private final int finalRound;
    private final MoveStrategy moveStrategy;

    public Race(RaceConfiguration configuration, MoveStrategy moveStrategy) {
        this.cars = configuration.getCars();
        this.finalRound = configuration.getRounds();
        this.moveStrategy = moveStrategy;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public void proceed() {
        for (int i = 1; i <= finalRound; i++) {
            proceedOneRound();
        }
    }

    private void proceedOneRound() {
        for (Car car : cars) {
            boolean isMovingForward = moveStrategy.isMoveable();
            if (isMovingForward) {
                car.move();
            }
        }
    }
}
