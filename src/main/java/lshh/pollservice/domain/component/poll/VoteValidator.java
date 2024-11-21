package lshh.pollservice.domain.component.poll;

import lshh.pollservice.common.exception.VoteException;
import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.domain.entity.ParticipationVote;
import lshh.pollservice.dto.participation.VoteCommand;
import org.springframework.stereotype.Component;

@Component
public class VoteValidator {

    public void validateTryVote(Poll poll) {
        if (!poll.isOpened()) {
            throw new VoteException("투표가 종료되었습니다.");
        }
    }
    public void validateUpdate(Poll poll, ParticipationVote participationVote, VoteCommand command) {
    }
}
