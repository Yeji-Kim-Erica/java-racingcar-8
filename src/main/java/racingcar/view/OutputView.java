package racingcar.view;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String CAR_NAME_INPUT_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";

    public void printCarNamePrompt() {
        System.out.println(CAR_NAME_INPUT_PROMPT);
    }
}
