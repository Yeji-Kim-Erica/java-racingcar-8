package racingcar.controller;

import racingcar.model.domain.Race;
import racingcar.model.domain.RaceConfiguration;
import racingcar.model.service.ConfigService;
import racingcar.model.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

/**
 * 자동차 경주 프로그램의 전체 흐름을 담당하는 클래스
 */
public class RacingGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ConfigService configService;
    private final RaceService raceService;

    public RacingGameController(InputView inputView, OutputView outputView,
                                ConfigService configService, RaceService raceService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.configService = configService;
        this.raceService = raceService;
    }

    public void run() {
        outputView.printCarNamesPrompt();
        String carNames = inputView.readCarNames();

        outputView.printGameRoundsPrompt();
        String gameRounds = inputView.readGameRounds();

        RaceConfiguration configuration = configService.createRaceConfiguration(carNames, gameRounds);
        Race race = raceService.proceedRace(configuration);
    }

}
