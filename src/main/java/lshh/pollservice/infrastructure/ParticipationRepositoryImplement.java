package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.participation.ParticipationRepository;
import lshh.pollservice.domain.entity.Participation;
import lshh.pollservice.dto.participation.ParticipationSimple;
import lshh.pollservice.infrastructure.jpa.ParticipationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ParticipationRepositoryImplement implements ParticipationRepository {
    private final ParticipationJpaRepository jpaRepository;

    @Override
    public Optional<Participation> findByUserIdAndPollId(Long userId, Long pollId) {
        return jpaRepository.findByUserIdAndPollId(userId, pollId);
    }

    @Override
    public Participation save(Participation participation) {
        return jpaRepository.save(participation);
    }

    @Override
    public List<Participation> findByPollId(Long pollId) {
        return jpaRepository.findByPollId(pollId);
    }

    @Override
    public Optional<Participation> findById(Long partitionId) {
        return jpaRepository.findById(partitionId);
    }
}
