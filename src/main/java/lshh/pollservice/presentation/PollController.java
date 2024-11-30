package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.PollService;
import lshh.pollservice.dto.poll.*;
import lshh.pollservice.dto.poll.schedule.PollScheduleCreateCommand;
import lshh.pollservice.dto.poll.schedule.PollScheduleDetail;
import lshh.pollservice.dto.poll.schedule.PollScheduleUpdateCommand;
import lshh.pollservice.presentation.component.PollResponseFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/poll")
@RequiredArgsConstructor
@RestController
public class PollController implements DefaultExceptionHandlable {
    private final PollService pollService;
    private final PollResponseFactory responseFactory;

    @GetMapping("/all")
    Map<String, Object> all() {
        var all = pollService.all();
        return responseFactory.ok(all);
    }

    @GetMapping("/list/{pageNo}/{pageSize}")
    Map<String, Object> list(@PathVariable(required = false) Integer pageNo, @PathVariable(required = false)  Integer pageSize) {
        pageNo = pageNo != null ? pageNo : 1;
        pageSize = pageSize != null ? pageSize : 10;
        var list = pollService.list(pageNo, pageSize);
        return responseFactory.ok(list);
    }

    @GetMapping("/detail/{id}")
    Map<String, Object> detail(@PathVariable Long id) {
        PollSimple result = pollService.detail(id);
        return responseFactory.ok(result);
    }


    @PostMapping("/create")
    Map<String, Object> create(@RequestBody PollScheduleCreateCommand command) {
        PollScheduleDetail result = pollService.create(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/update")
    Map<String, Object> update(@RequestBody PollScheduleUpdateCommand command) {
        PollSimple result = pollService.update(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/book")
    Map<String, Object> book(@RequestBody PollBookCommand command) {
        PollSimple result = pollService.book(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/open")
    Map<String, Object> open(@RequestBody PollOpenCommand command) {
        PollSimple result = pollService.open(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/close")
    Map<String, Object> close(@RequestBody PollCloseCommand command) {
        PollSimple result = pollService.close(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/quit")
    Map<String, Object> quit(@RequestBody PollQuitCommand command) {
        PollSimple result = pollService.quit(command);
        return responseFactory.ok(result);
    }
}
