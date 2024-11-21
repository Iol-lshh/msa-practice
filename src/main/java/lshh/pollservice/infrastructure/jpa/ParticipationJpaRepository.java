package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationJpaRepository extends JpaRepository<Participation, Long> {

    Optional<Participation> findByUserIdAndPollId(Long userId, Long pollId);

    List<Participation> findByPollId(Long pollId);
}
