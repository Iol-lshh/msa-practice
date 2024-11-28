package lshh.pollservice.domain.component.schedule;

import lshh.pollservice.domain.component.EntityFactory;
import lshh.pollservice.domain.entity.schedule.Schedule;
import lshh.pollservice.dto.schedule.ScheduleCreateCommand;
import org.springframework.stereotype.Component;

@Component
public class ScheduleFactory implements EntityFactory<Schedule> {

    public Schedule generate(ScheduleCreateCommand command) {
        return Schedule.builder()
                .startAt(command.startAt())
                .endAt(command.endAt())
                .state(command.state())
                .build();
    }
}
