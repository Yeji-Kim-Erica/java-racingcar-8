package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.model.service.ConfigService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ConfigService configService = new ConfigService();
        RacingGameController controller = new RacingGameController(inputView, outputView, configService);

        controller.run();
    }
}
