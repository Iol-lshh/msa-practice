package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.poll.PollRepository;
import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.infrastructure.jpa.PollJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PollRepositoryImplement extends AbstractRepositoryWithJpa<Poll, Long>
        implements PollRepository {
    private final PollJpaRepository jpaRepository;

    @Override
    protected JpaRepository<Poll, Long> jpaRepository() {
        return this.jpaRepository;
    }
}
