package racingcar.model.domain;

/**
 * 자동차의 이동 조건을 결정하는 인터페이스
 */
public interface MoveStrategy {
    boolean isMoveable();
}
