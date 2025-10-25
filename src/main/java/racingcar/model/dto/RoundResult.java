package racingcar.model.dto;

import racingcar.model.domain.Car;

import java.util.*;

/**
 * 차수별 경기 진행 결과를 저장하는 클래스
 */
public class RoundResult {
    private final Map<String, Integer> carPositions;

    public RoundResult() {
        carPositions = new LinkedHashMap<>();
    }

    public RoundResult(List<Car> cars) {
        carPositions = new LinkedHashMap<>();
        for (Car car : cars) {
            String name = car.getName();
            int position = car.getPosition();
            carPositions.put(name, position);
        }
    }

    public Map<String, Integer> getCarPositions() {
        return Collections.unmodifiableMap(carPositions);
    }

    public void add(Car car) {
        carPositions.put(car.getName(), car.getPosition());
    }
}
