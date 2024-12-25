package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.participation.ParticipationRepository;
import lshh.pollservice.domain.entity.participation.Participation;
import lshh.pollservice.infrastructure.jpa.ParticipationJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ParticipationRepositoryImplement extends AbstractRepositoryWithJpa<Participation, Long>
        implements ParticipationRepository {
    private final ParticipationJpaRepository jpaRepository;

    @Override
    protected JpaRepository<Participation, Long> jpaRepository() {
        return this.jpaRepository;
    }

    @Override
    public Optional<Participation> findByUserIdAndPollId(Long userId, Long pollId) {
        return jpaRepository.findByUserIdAndPollId(userId, pollId);
    }

    @Override
    public List<Participation> findByPollId(Long pollId) {
        return jpaRepository.findByPollId(pollId);
    }
}
