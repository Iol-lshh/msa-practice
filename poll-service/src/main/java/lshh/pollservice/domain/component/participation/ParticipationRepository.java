package lshh.pollservice.domain.component.participation;

import lshh.pollservice.common.exception.PersistenceNotFoundException;
import lshh.pollservice.domain.component.EntityRepository;
import lshh.pollservice.domain.entity.participation.Participation;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends EntityRepository<Participation, Long> {
    Optional<Participation> findByUserIdAndPollId(Long userId, Long pollId);
    default Participation getByUserIdAndPollId(Long userId, Long pollId){
        return findByUserIdAndPollId(userId, pollId).orElseThrow(()->new PersistenceNotFoundException("Participation not found"));
    }

    Participation save(Participation participation);

    List<Participation> findByPollId(Long pollId);
}
