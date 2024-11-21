package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollJpaRepository extends JpaRepository<Poll, Long> {

}
