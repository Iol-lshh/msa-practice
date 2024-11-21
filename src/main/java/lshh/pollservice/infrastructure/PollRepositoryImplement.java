package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.poll.PollRepository;
import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.infrastructure.jpa.PollJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PollRepositoryImplement implements PollRepository {

    private final PollJpaRepository jpaRepository;

    @Override
    public Poll save(Poll poll) {
        return jpaRepository.save(poll);
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
