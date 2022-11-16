package kr.or.ddit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.AgentsVO;


public interface AgentsService {

	// 목록 보기
	public List<AgentsVO> Aglist();

	public AgentsVO Agdetail(AgentsVO agentsVO);

}
