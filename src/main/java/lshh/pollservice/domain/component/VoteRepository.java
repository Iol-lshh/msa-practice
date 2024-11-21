package lshh.pollservice.domain.component;

import lshh.pollservice.domain.entity.ParticipationVote;

import java.util.Optional;

public interface VoteRepository {
    Optional<ParticipationVote> findByUserIdAndPollId(Long userId, Long pollId);
}
