package lshh.pollservice.domain.component.participation;

import lshh.pollservice.domain.entity.Participation;
import org.springframework.stereotype.Component;

@Component
public class ParticipationFactory {
    public Participation generate(Long userId, Long pollId) {
        return Participation.builder()
                .userId(userId)
                .pollId(pollId)
                .build();
    }
}
