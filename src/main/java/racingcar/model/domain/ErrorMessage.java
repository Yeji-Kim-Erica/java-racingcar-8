package racingcar.model.domain;

/**
 * domain 내에서 발생할 수 있는 오류 메시지를 정의한 클래스
 */
public enum ErrorMessage {
    CAR_NAME_LENGTH_OVER_MAXIMUM("자동차 이름의 길이는 5글자를 초과할 수 없습니다."),
    CAR_NAME_NULL_OR_EMPTY("자동차 이름은 공백이거나 비어 있을 수 없습니다."),
    DUPLICATED_CAR_NAME("자동차 이름은 중복될 수 없습니다."),
    CARS_LESS_THAN_MINIMUM("경기할 자동차의 수는 최소 2대 이상이어야 합니다.");

    private final String message;
    private static final String ERROR_PREFIX = "[오류 발생] ";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
