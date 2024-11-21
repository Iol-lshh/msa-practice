package lshh.pollservice.domain;

import lshh.pollservice.dto.poll.*;
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
class PollServiceTest {
    @Autowired
    private PollService pollService;
    @Autowired
    private ScheduleService scheduleService;

    @Nested
    class CreateTest {
        @Test
        void create() {
            ScheduleCreateCommand scheduleCreateCommand = new ScheduleCreateCommand(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule = scheduleService.create(scheduleCreateCommand);
            ScheduleCreateCommand scheduleCreateCommand2= new ScheduleCreateCommand(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule2 = scheduleService.create(scheduleCreateCommand);
            assertNotNull(schedule);
            assertNotNull(schedule2);
            System.out.println(schedule);
            System.out.println(schedule2);
            // given
            PollCreateCommand command = new PollCreateCommand(
                    "title",
                    "description",
                    PollState.OPENED,
                    SelectPollType.ONE_VOTE,
                    List.of(
                            new PollOptionRequest(schedule.id()),
                            new PollOptionRequest(schedule2.id())
                    )
            );

            // when
            PollDetail result = pollService.create(command);
            // then
            assertNotNull(result);
            assertNotNull(result.id());
            assertEquals(2, result.options().size());
            result.options().forEach(option -> {
                assertNotNull(option.id());
            });
        }
    }
}