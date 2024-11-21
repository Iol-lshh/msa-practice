package lshh.pollservice.domain.component.participation;

import lshh.pollservice.common.exception.PersistencNotFoundException;
import lshh.pollservice.domain.entity.Participation;
import lshh.pollservice.dto.participation.ParticipationSimple;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface ParticipationRepository {
    Optional<Participation> findByUserIdAndPollId(Long userId, Long pollId);
    default Participation getByUserIdAndPollId(Long userId, Long pollId){
        return findByUserIdAndPollId(userId, pollId).orElseThrow(()->new PersistencNotFoundException("Participation not found"));
    }

    Participation save(Participation participation);

    List<Participation> findByPollId(Long pollId);

    Optional<Participation> findById(Long partitionId);
    default Participation getById(Long partitionId){
        return findById(partitionId).orElseThrow(()->new PersistencNotFoundException("Participation not found"));
    }
}
