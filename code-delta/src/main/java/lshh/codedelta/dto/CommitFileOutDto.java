package lshh.codedelta.dto;

import lshh.core.lib.type.OutputDto;

public record CommitFileOutDto(
	String commitHash,
	String filePath,
	String contents
) implements OutputDto {
}
