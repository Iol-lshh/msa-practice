package lshh.pollservice.domain.component.participation;

import lshh.pollservice.domain.entity.Participation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ParticipationFactory {
    public Participation generate(Long userId, Long pollId) {
        return Participation.builder()
                .userId(userId)
                .pollId(pollId)
                .voteList(new ArrayList<>())
                .build();
    }
}
