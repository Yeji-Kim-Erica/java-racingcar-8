package racingcar.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.domain.RaceConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ConfigService 클래스")
public class ConfigServiceTest {
    private ConfigService configService;

    private static final String VALID_ROUNDS = "3";

    @BeforeEach
    void setUp() {
        configService = new ConfigService();
    }

    @Nested
    @DisplayName("성공 테스트")
    class SuccessionTest {
        @ParameterizedTest()
        @DisplayName("유효한 자동차 이름으로 경기 설정 객체 생성")
        @ValueSource(strings = {"pobi,woni,jun", "pobi ,  woni  ,jun"})
        void createRaceConfiguration(String input) {
            // when
            RaceConfiguration configuration = configService.createRaceConfiguration(input, VALID_ROUNDS);

            // then
            assertThat(configuration.getCars()).hasSize(3);
            assertThat(configuration.getCars().get(1).getName()).isEqualTo("woni");
            assertThat(configuration.getRounds()).isEqualTo(3);
        }
    }
}
