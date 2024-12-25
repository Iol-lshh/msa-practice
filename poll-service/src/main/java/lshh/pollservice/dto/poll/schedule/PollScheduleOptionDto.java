package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.domain.entity.poll.Poll;
import lshh.pollservice.domain.entity.poll.PollScheduleOption;
import lshh.pollservice.dto.OutputDto;

import java.util.List;

public record PollScheduleOptionDto(
        Long id,
        Long pollId,
        Long scheduleId
) implements OutputDto {
    public static PollScheduleOptionDto from(PollScheduleOption pollScheduleOption) {
        return new PollScheduleOptionDto(
                pollScheduleOption.getId(),
                pollScheduleOption.getPoll().getId(),
                pollScheduleOption.getScheduleId()
        );
    }

    public PollScheduleOption toEntity() {
        return PollScheduleOption.builder()
                .id(id())
                .poll(Poll.builder().id(pollId()).build())
                .scheduleId(scheduleId())
                .build();
    }

    public static List<PollScheduleOption> toEntityAll(List<PollScheduleOptionDto> options) {
        return options.stream()
                .map(PollScheduleOptionDto::toEntity)
                .toList();
    }
}
