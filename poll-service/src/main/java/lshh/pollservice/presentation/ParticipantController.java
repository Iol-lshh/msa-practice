package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.core.lib.type.DefaultExceptionHandlable;
import lshh.pollservice.domain.ParticipationService;
import lshh.pollservice.dto.participation.VoteCommand;
import lshh.pollservice.dto.participation.VoteUpdateCommand;
import lshh.pollservice.presentation.component.ParticipationResponseFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/participant")
@RequiredArgsConstructor
@RestController
public class ParticipantController implements DefaultExceptionHandlable {
    private final ParticipationService participationService;
    private final ParticipationResponseFactory responseFactory;

    @GetMapping("/list/{pollId}")
    public Map<String, Object> listByPollId(@PathVariable Long pollId) {
        var list = participationService.listByPollId(pollId);
        return responseFactory.ok(list);
    }

    @GetMapping("/detail/{partitionVoteId}")
    public Map<String, Object> detail(@PathVariable Long partitionVoteId) {
        var result = participationService.detail(partitionVoteId);
        return responseFactory.ok(result);
    }

    @PostMapping("/vote")
    public Map<String, Object> vote(VoteCommand command) {
        var result = participationService.vote(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/update/vote")
    public Map<String, Object> updateVote(VoteUpdateCommand command) {
        var result = participationService.updateVote(command);
        return responseFactory.ok(result);
    }
}
