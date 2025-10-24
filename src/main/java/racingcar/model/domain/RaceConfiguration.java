package racingcar.model.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 경기 설정 도메인 클래스
 */
public class RaceConfiguration {
    private final List<Car> cars;
    private final int rounds;

    private static final int MINIMUM_NUMBER_OF_CARS = 2;

    public RaceConfiguration(List<Car> cars, int rounds) {
        validateCarList(cars);
        this.cars = cars;
        this.rounds = rounds;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public int getRounds() {
        return rounds;
    }

    private void validateCarList(List<Car> cars) {
        boolean isNumberOfCarsUnderMinimum = (cars.size() < MINIMUM_NUMBER_OF_CARS);
        if (isNumberOfCarsUnderMinimum) {
            throw new IllegalArgumentException(ErrorMessage.CARS_LESS_THAN_MINIMUM.getMessage());
        }

        if (hasDuplicatedCarName(cars)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CAR_NAME.getMessage());
        }
    }

    private boolean hasDuplicatedCarName(List<Car> cars) {
        List<String> carNames = new ArrayList<>();
        for (Car car : cars) {
            carNames.add(car.getName());
        }
        Set<String> carNamesWithNoDuplication = new HashSet<>(carNames);
        return (carNames.size() != carNamesWithNoDuplication.size());
    }
}
