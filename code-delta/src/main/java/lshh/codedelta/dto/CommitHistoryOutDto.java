package lshh.codedelta.dto;

import java.util.List;

import lshh.codedelta.entity.CommitHistoryEntity;
import lshh.core.lib.type.OutputDto;

public record CommitHistoryOutDto(
	Long id,
	List<CommitDiffQueryOutDto> queryDiffs
) implements OutputDto {
	public static CommitHistoryOutDto from(CommitHistoryEntity result) {
		List<CommitDiffQueryOutDto> queryDiffs = CommitDiffQueryOutDto.ofAll(result.getQueryDiffs());
		return new CommitHistoryOutDto(result.getId(), queryDiffs);
	}
}
