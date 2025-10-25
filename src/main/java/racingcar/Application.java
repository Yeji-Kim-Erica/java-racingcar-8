package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.model.domain.MoveStrategy;
import racingcar.model.domain.RandomMoveStrategy;
import racingcar.model.service.ConfigService;
import racingcar.model.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ConfigService configService = new ConfigService();
        MoveStrategy moveStrategy = new RandomMoveStrategy();
        RaceService raceService = new RaceService(moveStrategy);
        RacingGameController controller = new RacingGameController(inputView, outputView, configService, raceService);

        controller.run();
    }
}
