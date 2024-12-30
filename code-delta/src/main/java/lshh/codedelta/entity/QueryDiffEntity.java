package lshh.codedelta.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lshh.codedelta.common.StringQueryParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lshh.codedelta.dto.CommitFileDiffOutDto;
import lshh.codedelta.dto.CommitFileOutDto;
import lshh.core.lib.type.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "QUERY_DIFF")
public class QueryDiffEntity extends BaseEntity {

	@Id
	@Column(name = "DIFF_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	@JoinColumn(name = "COMMIT_ID")
	CommitHistoryEntity commitHistory;

	@Column(name = "QUERY_PATH")
	String queryPath;
	@Column(name = "COMMIT_CONT")
	String commitContents;
	@Column(name = "RECEN_CONT")
	String recentContents;
	@Column(name = "RECEN_ID")
	Long recentCommitId;
	@Column(name = "APPR_YN")
	String approveYn;
	@Column(name = "CHAN_TYPE")
	String changeType;

	public static List<QueryDiffEntity> ofAll(
			CommitFileDiffOutDto fileDiffDto, CommitHistoryEntity newCommitHistory
	) {
		CommitFileOutDto oldFile = fileDiffDto.oldFile();
		Map<String, String> oldQueryMap = StringQueryParser.extractQueries(oldFile.contents());
		CommitFileOutDto newFile = fileDiffDto.newFile();
		Map<String, String> newQueryMap = StringQueryParser.extractQueries(newFile.contents());
		List<QueryDiffEntity> queryDiffs = new ArrayList<>();
		// 새로운 쿼리와 이전 쿼리 비교
		for (String methodName : newQueryMap.keySet()) {
			String newQuery = newQueryMap.get(methodName);
			String oldQuery = oldQueryMap.get(methodName);
			if (oldQuery == null || oldQuery.isEmpty()) {
				oldQuery = "";
			}
			if (!newQuery.equals(oldQuery)) {
				queryDiffs.add(QueryDiffEntity.of(fileDiffDto, newCommitHistory, methodName, newQuery, oldQuery));
			}
		}
		// 삭제된 메서드
		for (String methodName : oldQueryMap.keySet()) {
			if (!newQueryMap.containsKey(methodName)) {
				queryDiffs.add(
					QueryDiffEntity.of(fileDiffDto, newCommitHistory, methodName, "", oldQueryMap.get(methodName)));
			}
		}
		return queryDiffs;
	}

	public static List<QueryDiffEntity> ofAll(
		CommitFileDiffOutDto fileDiffDto, CommitHistoryEntity oldCommitHistory, CommitHistoryEntity newCommitHistory
	) {
		CommitFileOutDto oldFile = fileDiffDto.oldFile();
		Map<String, String> oldQueryMap = StringQueryParser.extractQueries(oldFile.contents());
		CommitFileOutDto newFile = fileDiffDto.newFile();
		Map<String, String> newQueryMap = StringQueryParser.extractQueries(newFile.contents());
		List<QueryDiffEntity> queryDiffs = new ArrayList<>();
		// 새로운 쿼리와 이전 쿼리 비교
		for (String methodName : newQueryMap.keySet()) {
			String newQuery = newQueryMap.get(methodName);
			String oldQuery = oldQueryMap.get(methodName);
			if (oldQuery == null || oldQuery.isEmpty()) {
				oldQuery = "";
			}
			if (!newQuery.equals(oldQuery)) {
				queryDiffs.add(
					QueryDiffEntity.of(fileDiffDto, oldCommitHistory, newCommitHistory, methodName, newQuery,
						oldQuery));
			}
		}
		// 삭제된 메서드
		for (String methodName : oldQueryMap.keySet()) {
			if (!newQueryMap.containsKey(methodName)) {
				queryDiffs.add(QueryDiffEntity.of(fileDiffDto, oldCommitHistory, newCommitHistory, methodName, "",
					oldQueryMap.get(methodName)));
			}
		}
		return queryDiffs;
	}

	public static QueryDiffEntity of(
		CommitFileDiffOutDto fileDiffDto,
		CommitHistoryEntity comparingCommitHistory, CommitHistoryEntity commitHistory,
		String methodName, String content, String recentContent
	) {
		QueryDiffEntity result = of(fileDiffDto, commitHistory, methodName, content, recentContent);
		result.setRecentCommitId(comparingCommitHistory.getId());
		return result;
	}

	public static QueryDiffEntity of(
		CommitFileDiffOutDto fileDiffDto,
		CommitHistoryEntity commitHistory,
		String methodName, String content, String recentContent
	) {
		String filePath = fileDiffDto.path();
		filePath = filePath.substring(filePath.indexOf("kr/co/milkt/"))
			.replace(".java", "")
			.replaceAll("/", ".");
		return QueryDiffEntity.builder()
			.commitHistory(commitHistory)
			.queryPath(filePath + "::" + methodName)
			.commitContents(content)
			.recentContents(recentContent)
			.approveYn("N")
			.changeType(fileDiffDto.changeType())
			.build();
	}
}
