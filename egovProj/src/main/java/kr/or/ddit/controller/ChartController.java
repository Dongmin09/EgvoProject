package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/chart")
@Slf4j
@Controller
public class ChartController {

	// 요청URI : /chart/chart01
	//json 구글차트
	@GetMapping("/chart01")
	public String chart01() {
		
		//forwarding
		return "chart/chart01";
	}
	
	
	
	// 요청URI : /chart/chart01Multi
	//json 구글 차트 멀티
	@GetMapping("/chart01Multi")
	public String chart01Multi() {
		
		//forwarding
		return "chart/chart01Multi";
	}
	
	
	
	// 요청URI : /chart/chart02
	//oracle 구글차트
	@GetMapping("/chart02")
	public String chart2() {
		
		//forwarding
		return "chart/chart02";
	}
	
	
	
}
