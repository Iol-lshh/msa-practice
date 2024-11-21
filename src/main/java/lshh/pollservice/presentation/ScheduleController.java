package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.ScheduleService;
import lshh.pollservice.dto.schedule.ScheduleCreateCommand;
import lshh.pollservice.presentation.component.ScheduleResponseFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/schedule")
@RequiredArgsConstructor
@RestController
public class ScheduleController implements DefaultExceptionHandlable {
    private final ScheduleService scheduleService;
    private final ScheduleResponseFactory responseFactory;

    @RequestMapping("/list")
    public Map<String, Object> list() {
        var list = scheduleService.list();
        return responseFactory.ok(list);
    }

    @RequestMapping("/detail")
    public Map<String, Object> detail(Long id) {
        var result = scheduleService.detail(id);
        return responseFactory.ok(result);
    }

    @RequestMapping("/create")
    public Map<String, Object> create(@RequestBody ScheduleCreateCommand command) {
        var result = scheduleService.create(command);
        return responseFactory.ok(result);
    }
}
