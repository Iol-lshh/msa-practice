package lshh.pollservice.domain.component.poll;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.dto.poll.PollCreateCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class PollFactory {
    public Poll generate(PollCreateCommand command) {
        Poll poll = Poll.builder()
                .title(command.title())
                .description(command.description())
                .state(command.state())
                .type(command.type())
                .options(new ArrayList<>())
                .build();
        poll.addAll(command.options());
        return poll;
    }
}
