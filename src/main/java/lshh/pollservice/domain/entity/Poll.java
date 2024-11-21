package lshh.pollservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.dto.poll.PollTypeDbConverter;
import lshh.pollservice.dto.poll.PollOptionRequest;
import lshh.pollservice.dto.poll.PollState;
import lshh.pollservice.dto.poll.PollType;
import lshh.pollservice.dto.poll.SelectPollType;

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
    PollType type;

    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PollScheduleOption> options;

    public void addAll(List<PollOptionRequest> options) {
        for (PollOptionRequest option : options) {
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
        return type == SelectPollType.MULTI_VOTE;
    }
}
