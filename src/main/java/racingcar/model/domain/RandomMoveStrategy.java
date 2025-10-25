package racingcar.model.domain;

import camp.nextstep.edu.missionutils.Randoms;

/**
 * 자동차의 이동 조건을 난수로 결정하는 클래스
 */
public class RandomMoveStrategy implements MoveStrategy{
    private static final int MOVING_CONDITION_DETERMINATION_NUMBER = 4;

    @Override
    public boolean isMoveable() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= MOVING_CONDITION_DETERMINATION_NUMBER;
    }
}
