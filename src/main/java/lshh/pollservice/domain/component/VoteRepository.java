package lshh.pollservice.domain.component;

import lshh.pollservice.domain.entity.ParticipationScheduleOption;

import java.util.Optional;

public interface VoteRepository {
    Optional<ParticipationScheduleOption> findByUserIdAndPollId(Long userId, Long pollId);
}
