package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.dto.participation.VoteCommand;
import lshh.pollservice.dto.participation.VoteUpdateCommand;
import lshh.pollservice.dto.poll.type.PollState;
import lshh.pollservice.dto.poll.type.SelectPollOptionType;
import lshh.pollservice.dto.poll.schedule.PollScheduleCreateCommand;
import lshh.pollservice.dto.poll.schedule.PollScheduleDetail;
import lshh.pollservice.dto.poll.schedule.PollScheduleOptionDto;
import lshh.pollservice.dto.poll.schedule.PollScheduleOptionInputDto;
import lshh.pollservice.dto.schedule.ScheduleCreate;
import lshh.pollservice.dto.schedule.ScheduleDetail;
import lshh.pollservice.dto.schedule.ScheduleState;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ParticipationServiceTest {
    @Autowired
    private PollService pollService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ScheduleService scheduleService;

    @Nested
    class ParticipationScheduleOptionTest {
        @Test
        void vote() {
            ScheduleCreate scheduleCreate = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule = scheduleService.create(scheduleCreate);
            ScheduleCreate scheduleCreate2 = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule2 = scheduleService.create(scheduleCreate2);
            assertNotNull(schedule);
            assertNotNull(schedule2);
            log.info(schedule.toString());
            log.info(schedule2.toString());
            PollScheduleCreateCommand preTestCommand = new PollScheduleCreateCommand(
                    "title",
                    "description",
                    PollState.OPENED,
                    SelectPollOptionType.ONE_VOTE,
                    List.of(
                            new PollScheduleOptionInputDto(schedule.id()),
                            new PollScheduleOptionInputDto(schedule2.id())
                    )
            );
            PollScheduleDetail poll = pollService.create(preTestCommand);
            assertNotNull(poll);
            log.info(poll.toString());

            VoteCommand command = new VoteCommand(1L, poll.id(), poll.options().stream().map(PollScheduleOptionDto::id).toList());
            var result = participationService.vote(command);

            assertNotNull(result);
            assertEquals(2, result.voted().size());
            log.info(result.toString());

        }
    }

    @Nested
    class ParticipationUpdateVoteTest {
        @Test
        void vote() {
            ScheduleCreate scheduleCreate = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule = scheduleService.create(scheduleCreate);
            ScheduleCreate scheduleCreate2 = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule2 = scheduleService.create(scheduleCreate2);
            assertNotNull(schedule);
            assertNotNull(schedule2);
            log.info(schedule.toString());
            log.info(schedule2.toString());
            PollScheduleCreateCommand preTestCommand = new PollScheduleCreateCommand(
                    "title",
                    "description",
                    PollState.OPENED,
                    SelectPollOptionType.ONE_VOTE,
                    List.of(
                            new PollScheduleOptionInputDto(schedule.id()),
                            new PollScheduleOptionInputDto(schedule2.id())
                    )
            );
            PollScheduleDetail poll = pollService.create(preTestCommand);
            assertNotNull(poll);
            log.info(poll.toString());
            VoteCommand command = new VoteCommand(1L, poll.id(), poll.options().stream().map(PollScheduleOptionDto::id).toList());
            participationService.vote(command);

            VoteUpdateCommand updateCommand = new VoteUpdateCommand(1L, poll.id(), poll.options().stream().findFirst().map(PollScheduleOptionDto::id).stream().toList());
            var result = participationService.updateVote(updateCommand);

            assertNotNull(result);
            assertEquals(1, result.voted().size());
            log.info(result.toString());

        }
    }
}