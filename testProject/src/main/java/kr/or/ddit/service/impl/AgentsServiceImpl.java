package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.AgentsMapper;
import kr.or.ddit.service.AgentsService;
import kr.or.ddit.vo.AgentsVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AgentsServiceImpl implements AgentsService {
	
	@Autowired
	AgentsMapper agentsMapper;
	
	// 목록 보기
	@Override
	public List<AgentsVO> Aglist(){
		
		return this.agentsMapper.Aglist();
	}
	
	// 상세 보기
	@Override
	public AgentsVO Agdetail(AgentsVO agentsVO) {
		log.info("return : " + this.agentsMapper.Agdetail(agentsVO));
		
		return this.agentsMapper.Agdetail(agentsVO);
	}
}
