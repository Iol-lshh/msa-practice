package lshh.pollservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.dto.poll.*;
import lshh.pollservice.dto.poll.type.PollContentsType;
import lshh.pollservice.dto.poll.type.PollOptionType;
import lshh.pollservice.dto.poll.type.PollState;
import lshh.pollservice.dto.poll.type.SelectPollOptionType;
import lshh.pollservice.dto.poll.schedule.PollScheduleOptionRequest;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    @Enumerated(EnumType.STRING)
    PollState state;
    @Convert(converter = PollTypeDbConverter.class)
    PollOptionType type;
    @Enumerated(EnumType.STRING)
    PollContentsType contentsType;

    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PollScheduleOption> options;

    public void addAll(List<PollScheduleOptionRequest> options) {
        for (PollScheduleOptionRequest option : options) {
            this.options.add(PollScheduleOption.builder()
                    .scheduleId(option.scheduleId())
                    .poll(this)
                    .build());
        }
    }

    public boolean isOpened() {
        return state == PollState.OPENED;
    }

    public boolean isMultiVote() {
        return type == SelectPollOptionType.MULTI_VOTE;
    }
}
