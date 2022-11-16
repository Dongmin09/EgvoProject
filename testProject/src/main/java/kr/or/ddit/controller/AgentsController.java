package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.service.AgentsService;
import kr.or.ddit.vo.AgentsVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/agents")
@Controller
public class AgentsController {

	@Autowired
	AgentsService agentsService;
	
	@GetMapping("/Aglist")
	public String Aglist(Model model) {
		
		List<AgentsVO> agentsVOList =  this.agentsService.Aglist();
		
		log.info("agentsVOList : " + agentsVOList.toString());
		
		model.addAttribute("agentsVOList",agentsVOList);
		
		//forwarding
		return "agents/Aglist";
		
	}
	
	@GetMapping("/Agdetail")
	public String Agdetail(AgentsVO agentsVO,Model model) {
		
		// 상세 데이터 가져오기
		AgentsVO agVO = this.agentsService.Agdetail(agentsVO);
		log.info("agentsVO :" + agVO);
		
		model.addAttribute("AgentsVO", agVO);
		
		//forwarding
		return "agents/Agwrite";
	}
	
}
