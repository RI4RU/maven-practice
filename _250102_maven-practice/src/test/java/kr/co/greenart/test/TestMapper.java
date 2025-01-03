package kr.co.greenart.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.co.greenart.db.DBTimeMapper;
import kr.co.greenart.util.DBUtil;

public class TestMapper {
	@Test
	public void test() {
		try (SqlSession session = DBUtil.getSqlSession()) {
			DBTimeMapper mapper = session.getMapper(DBTimeMapper.class);
			LocalDateTime result = mapper.selectDbTime();
			
			assertNotNull(result);
			assertEquals(2025, result.getYear());
		}
	}
}
