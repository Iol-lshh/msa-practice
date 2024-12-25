package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.core.lib.util.ClockManager;
import lshh.pollservice.domain.component.participation.ParticipationFactory;
import lshh.pollservice.domain.component.participation.ParticipationRepository;
import lshh.pollservice.domain.component.poll.PollRepository;
import lshh.pollservice.domain.component.poll.VoteValidator;
import lshh.pollservice.domain.entity.participation.Participation;
import lshh.pollservice.domain.entity.poll.Poll;
import lshh.core.lib.type.DefaultOutput;
import lshh.pollservice.dto.participation.ParticipationDetail;
import lshh.pollservice.dto.participation.ParticipationSimple;
import lshh.pollservice.dto.participation.VoteCommand;
import lshh.pollservice.dto.participation.VoteUpdateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParticipationService {
    private final ParticipationRepository repository;
    private final PollRepository pollRepository;
    private final VoteValidator validator;
    private final ParticipationFactory factory;
    private final ClockManager clockManager;

    @Transactional
    public ParticipationDetail vote(VoteCommand command) {
        Poll poll = pollRepository.getById(command.pollId());
        validator.validateTryVote(poll);
        Participation participation = factory.generate(command.userId(), command.pollId());
        participation.vote(command.optionIds(), clockManager.getClock());
        var result = repository.save(participation);
        return ParticipationDetail.from(result);
    }

    @Transactional
    public ParticipationDetail updateVote(VoteUpdateCommand command) {
        Poll poll = pollRepository.getById(command.pollId());
        validator.validateTryVote(poll);
        Participation participation = repository.getByUserIdAndPollId(command.userId(), command.pollId());
        participation.vote(command.optionIds(), clockManager.getClock());
        var result = repository.save(participation);
        return ParticipationDetail.from(result);
    }

    @Transactional(readOnly = true)
    public List<ParticipationSimple> listByPollId(Long pollId) {
        return repository.findByPollId(pollId).stream().map(ParticipationSimple::from).toList();
    }

    @Transactional
    public DefaultOutput participate(Long partitionVoteId) {
        return null;
    }

    @Transactional(readOnly = true)
    public ParticipationDetail detail(Long partitionId) {
        return ParticipationDetail.from(repository.getById(partitionId));
    }
}
