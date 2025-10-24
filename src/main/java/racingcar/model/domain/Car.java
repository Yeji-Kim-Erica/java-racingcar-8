package racingcar.model.domain;

/**
 * 자동차 도메인 클래스
 */
public class Car {
    private final String name;

    private static final int MAXIMUM_NAME_LENGTH = 5;

    public Car(String name) {
        String refinedName = refineName(name);
        validateName(refinedName);
        this.name = refinedName;
    }

    public String getName() {
        return name;
    }

    private String refineName(String name) {
        if (name == null) {
            return name;
        }
        return name.trim();
    }

    private void validateName(String name) {
        boolean isNullOrBlank = (name == null || name.isBlank());
        if (isNullOrBlank) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_NULL_OR_BLANK.getMessage());
        }

        boolean isNameLengthOverMaximum = (name.length() > MAXIMUM_NAME_LENGTH);
        if (isNameLengthOverMaximum) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_OVER_MAXIMUM.getMessage());
        }
    }
}
