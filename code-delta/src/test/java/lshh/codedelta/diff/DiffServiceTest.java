package lshh.codedelta.diff;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lshh.codedelta.infrastructure.CommitRepository;
import lshh.codedelta.service.DiffService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DiffServiceTest {
	@Autowired
	private DiffService utd;

	@Autowired
	private CommitRepository repository;

	@Nested
	class FindDiffMappersTest{
		@Test
		@Transactional
		public void 정상작동(){
// 			utd.executeDiffQueries("", "");
//			var resultCount = utd.executeDiffQueries("", "");
//			log.warn(""+resultCount);
//			assertNotEquals(0, resultCount);
//			List<CommitHistoryEntity> result = repository.findAll();
//			List<CommitHistoryOutDto> diffs = result.stream().map(CommitHistoryOutDto::from).toList();
//			log.warn(diffs.toString());
		}
	}
}