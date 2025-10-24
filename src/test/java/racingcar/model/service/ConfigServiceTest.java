package racingcar.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.domain.Car;
import racingcar.model.domain.ErrorMessage;
import racingcar.model.domain.RaceConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ConfigService 클래스")
public class ConfigServiceTest {
    private ConfigService configService;

    private static final String VALID_CAR_NAMES = "pobi,woni";
    private static final String VALID_ROUNDS = "3";

    @BeforeEach
    void setUp() {
        configService = new ConfigService();
    }

    @Nested
    @DisplayName("성공 테스트")
    class SuccessTest {
        @Test
        @DisplayName("유효한 자동차 이름과 경기 횟수로 경기 설정 객체 생성")
        void createRaceConfiguration() {
            // when
            RaceConfiguration configuration = configService.createRaceConfiguration(VALID_CAR_NAMES, VALID_ROUNDS);

            // then
            assertThat(configuration.getCars())
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni");
            assertThat(configuration.getRounds()).isEqualTo(3);
        }

        @ParameterizedTest
        @DisplayName("공백을 포함한 자동차 이름으로 경기 설정 객체 생성")
        @ValueSource(strings = {" pobi,woni, jun", "pobi ,  woni  ,jun"})
        void createRaceConfiguration(String input) {
            // when
            RaceConfiguration configuration = configService.createRaceConfiguration(input, VALID_ROUNDS);

            // then
            assertThat(configuration.getCars())
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni", "jun");
            assertThat(configuration.getRounds()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("예외 처리 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("자동차 이름이 5자를 초과하는 경우")
        void carNameLengthExceedsMaximum() {
            // given
            String input = "pobi,longlongname";

            // when & then
            assertThatThrownBy(() -> configService.createRaceConfiguration(input, VALID_ROUNDS))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CAR_NAME_LENGTH_OVER_MAXIMUM.getMessage());
        }

        @ParameterizedTest
        @DisplayName("자동차 이름이 null이거나, 빈 문자열이거나, 공백 문자로만 이루어져 있는 경우")
        @NullSource
        @EmptySource
        @ValueSource(strings = {"    ", " "})
        void carNameIsBlank(String input) {
            assertThatThrownBy(() -> configService.createRaceConfiguration(input, VALID_ROUNDS))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.CAR_NAME_NULL_OR_BLANK.getMessage());
        }

        @Test
        @DisplayName("자동차 이름이 중복되는 경우")
        void duplicatedCarName() {
            // given
            String duplicatedInput = "pobi,woni,pobi";

            // when & then
            assertThatThrownBy(() -> configService.createRaceConfiguration(duplicatedInput, VALID_ROUNDS))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.DUPLICATED_CAR_NAME.getMessage());
        }

        @ParameterizedTest
        @DisplayName("숫자로 변환할 수 없는 값을 시도 횟수로 입력받은 경우")
        @ValueSource(strings = {"", " ", "가", ".", " 3"})
        void roundsNotNumeric(String input) {
            assertThatThrownBy(() -> configService.createRaceConfiguration(VALID_CAR_NAMES, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.ROUNDS_NOT_CONVERTIBLE_TO_NUMERIC.getMessage());
        }

        @ParameterizedTest
        @DisplayName("시도 횟수가 양수가 아닌 경우")
        @ValueSource(strings = {"0", "-1"})
        void roundsNotPositive(String input) {
            assertThatThrownBy(() -> configService.createRaceConfiguration(VALID_CAR_NAMES, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.ROUNDS_NOT_POSITIVE.getMessage());
        }
    }
}
