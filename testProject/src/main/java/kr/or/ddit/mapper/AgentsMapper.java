package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.AgentsVO;

public interface AgentsMapper {

	// 리스트 보기
	public List<AgentsVO> Aglist();
	
	// 상세 보기
	public AgentsVO Agdetail(AgentsVO agentsVO);
}
