package racingcar.view;

import racingcar.model.dto.RoundResult;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String CAR_NAME_INPUT_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String GAME_ROUND_INPUT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String RACE_RESULT_INITIAL_LINE = "실행 결과";
    private static final String ROUND_RESULT_FORMAT = "%s : %s\n";
    private static final String CAR_DISTANCE_PER_ROUND = "-";
    private static final String WINNER_FORMAT = "최종 우승자 : %s";
    private static final String WINNER_DELIMITER = ", ";

    public void printCarNamesPrompt() {
        System.out.println(CAR_NAME_INPUT_PROMPT);
    }

    public void printGameRoundsPrompt() {
        System.out.println(GAME_ROUND_INPUT_PROMPT);
    }

    public void printRaceResult(List<RoundResult> raceResult) {
        System.out.println();
        System.out.println(RACE_RESULT_INITIAL_LINE);
        for (RoundResult roundResult : raceResult) {
            printRoundResult(roundResult);
        }
    }

    public void printWinner(List<String> winners) {
        String winner = alignNameOfWinners(winners);
        System.out.printf(WINNER_FORMAT, winner);
    }

    private void printRoundResult(RoundResult roundResult) {
        Map<String, Integer> resultMap = roundResult.getCarPositions();
        for (Entry<String, Integer> entry : resultMap.entrySet()) {
            String name = entry.getKey();
            int position = entry.getValue();
            System.out.printf(ROUND_RESULT_FORMAT, name, CAR_DISTANCE_PER_ROUND.repeat(position));
        }
        System.out.println();
    }

    private String alignNameOfWinners(List<String> winners) {
        boolean isSingleWinner = (winners.size() <= 1);
        if (isSingleWinner) {
            return winners.getFirst();
        }
        return String.join(WINNER_DELIMITER, winners);
    }
}
