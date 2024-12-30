package lshh.codedelta.dto;

import lshh.codedelta.entity.QueryDiffEntity;
import lshh.core.lib.type.OutputDto;

import java.util.List;


public record CommitDiffQueryOutDto(
	String recentContents,
	String commitContents
) implements OutputDto {

	public static List<CommitDiffQueryOutDto> ofAll(List<QueryDiffEntity> queryDiffs) {
		return queryDiffs.stream().map(CommitDiffQueryOutDto::from).toList();
	}
	public static CommitDiffQueryOutDto from(QueryDiffEntity diff){
	return new CommitDiffQueryOutDto(diff.getRecentContents(), diff.getCommitContents());
	}
}
