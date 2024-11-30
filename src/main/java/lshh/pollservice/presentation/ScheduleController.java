package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.ScheduleService;
import lshh.pollservice.dto.schedule.ScheduleCreateCommand;
import lshh.pollservice.presentation.component.ScheduleResponseFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/schedule")
@RequiredArgsConstructor
@RestController
public class ScheduleController implements DefaultExceptionHandlable {
    private final ScheduleService scheduleService;
    private final ScheduleResponseFactory responseFactory;

    @GetMapping("/all")
    public Map<String, Object> all() {
        var all = scheduleService.all();
        return responseFactory.ok(all);
    }

    @GetMapping("/list")
    public Map<String, Object> list(@PathVariable(required = false) Integer pageNo, @PathVariable(required = false)  Integer pageSize) {
        pageNo = pageNo != null ? pageNo : 1;
        pageSize = pageSize != null ? pageSize : 10;
        var list = scheduleService.list(pageNo, pageSize);
        return responseFactory.ok(list);
    }

    @GetMapping("/detail")
    public Map<String, Object> detail(Long id) {
        var result = scheduleService.detail(id);
        return responseFactory.ok(result);
    }

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody ScheduleCreateCommand command) {
        var result = scheduleService.create(command);
        return responseFactory.ok(result);
    }
}
