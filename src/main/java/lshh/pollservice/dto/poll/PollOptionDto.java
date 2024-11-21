package lshh.pollservice.dto.poll;

import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.domain.entity.PollScheduleOption;

import java.util.List;

public record PollOptionDto(
        Long id,
        Long pollId,
        Long scheduleId
) {
    public static PollOptionDto from(PollScheduleOption pollScheduleOption) {
        return new PollOptionDto(
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

    public static List<PollScheduleOption> toEntityAll(List<PollOptionDto> options) {
        return options.stream()
                .map(PollOptionDto::toEntity)
                .toList();
    }
}
