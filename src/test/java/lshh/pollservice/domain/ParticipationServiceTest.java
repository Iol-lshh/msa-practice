package lshh.pollservice.domain;

import lshh.pollservice.dto.poll.*;
import lshh.pollservice.dto.participation.VoteCommand;
import lshh.pollservice.dto.schedule.ScheduleCreateCommand;
import lshh.pollservice.dto.schedule.ScheduleDetail;
import lshh.pollservice.dto.schedule.ScheduleState;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParticipationServiceTest {
    @Autowired
    private PollService pollService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ScheduleService scheduleService;

    @Nested
    class ParticipationVoteTest {
        @Test
        void vote() {
            ScheduleCreateCommand scheduleCreateCommand = new ScheduleCreateCommand(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule = scheduleService.create(scheduleCreateCommand);
            ScheduleCreateCommand scheduleCreateCommand2= new ScheduleCreateCommand(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule2 = scheduleService.create(scheduleCreateCommand);
            assertNotNull(schedule);
            assertNotNull(schedule2);
            System.out.println(schedule);
            System.out.println(schedule2);
            PollCreateCommand preTestCommand = new PollCreateCommand(
                    "title",
                    "description",
                    PollState.OPENED,
                    SelectPollType.ONE_VOTE,
                    List.of(
                            new PollOptionRequest(schedule.id()),
                            new PollOptionRequest(schedule2.id())
                    )
            );
            PollDetail poll = pollService.create(preTestCommand);
            assertNotNull(poll);
            System.out.println(poll);

            VoteCommand command = new VoteCommand(1L, poll.id(), poll.options().stream().map(PollOptionDto::id).toList());
            var result = participationService.vote(command);

            assertNotNull(result);
            System.out.println(result);

        }
    }

    @Nested
    class UpdateTest {

    }
}