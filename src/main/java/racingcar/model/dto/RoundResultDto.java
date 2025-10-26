package racingcar.model.dto;

import racingcar.model.domain.Car;

import java.util.*;

/**
 * 차수별 경기 진행 결과를 전달하기 위한 데이터 전송 객체
 */
public class RoundResultDto {
    private final Map<String, Integer> carPositions;

    public RoundResultDto() {
        carPositions = new LinkedHashMap<>();
    }

    public RoundResultDto(List<Car> cars) {
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
