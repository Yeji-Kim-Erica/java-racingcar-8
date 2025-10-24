package racingcar.model.service;

import racingcar.model.domain.Car;
import racingcar.model.domain.ErrorMessage;
import racingcar.model.domain.RaceConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 경기 설정 관련 로직을 담당하는 클래스
 */
public class ConfigService {
    private static final String CAR_NAMES_DELIMITER = ",";

    public RaceConfiguration createRaceConfiguration(String carNames, String roundsString) {
        List<Car> cars = createListOfCarFromInput(carNames);
        int rounds = parseRoundsStringToInt(roundsString);
        return new RaceConfiguration(cars, rounds);
    }

    private List<Car> createListOfCarFromInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_NULL_OR_BLANK.getMessage());
        }

        String[] carNameArray = input.split(CAR_NAMES_DELIMITER);
        List<Car> carList = new ArrayList<>();
        for (String carName : carNameArray) {
            Car car = new Car(carName);
            carList.add(car);
        }
        return carList;
    }

    private int parseRoundsStringToInt(String roundsString) {
        try {
            return Integer.parseInt(roundsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ROUNDS_NOT_CONVERTIBLE_TO_NUMERIC.getMessage());
        }
    }
}
