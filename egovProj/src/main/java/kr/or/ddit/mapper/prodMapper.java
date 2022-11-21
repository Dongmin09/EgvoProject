package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

// 매퍼 인터페이스
public interface prodMapper {
	
	//도서 목록
	public List<Map<String, Object>> amtSale();
}
