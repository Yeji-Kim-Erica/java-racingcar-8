package racingcar.model.dto;

import java.util.Collections;
import java.util.List;

/**
 * 경주 실행의 최종 결과(라운드별 기록 및 우승자 목록)를 전달하기 위한 데이터 전송 객체
 */
public class RaceResultDto {
    private final List<RoundResultDto> roundResultDtoList;
    private final List<String> winners;

    public RaceResultDto(List<RoundResultDto> roundResultDtoList, List<String> winners) {
        this.roundResultDtoList = roundResultDtoList;
        this.winners = winners;
    }

    public List<RoundResultDto> getRoundResultList() {
        return Collections.unmodifiableList(roundResultDtoList);
    }

    public List<String> getWinners() {
        return Collections.unmodifiableList(winners);
    }
}
