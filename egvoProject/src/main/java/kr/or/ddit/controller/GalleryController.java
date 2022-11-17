package kr.or.ddit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.GalleryService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@RequestMapping("/gallery")
@Controller
@Slf4j
public class GalleryController {
	
	//DI 의존성 주입
	@Autowired
	GalleryService galleryService;

	// 이미지 목록
	// 요청URI :  gallery/list
	// 요청파라미터 : ?bookId=3
	@GetMapping("/list")
	public String list(Model model, @ModelAttribute BookVO bookVO) {
		
		bookVO = this.galleryService.list(bookVO);
		
		log.info("BookVO: " + bookVO);
		
		// 공통 약속
		model.addAttribute("bodyTitle", "이미지 목록");
		model.addAttribute("bookVO", bookVO);
		
		
		//forwarding
		return "gallery/list";
	}
	
	
	// 요청URI : /gallery/bookList
	//방식 :get
	//도서목록 가져와서 select에 추가하기
	//json 데이터로 리턴
	@ResponseBody
	@GetMapping("/bookList")
	public List<BookVO> bookList() {
		List<BookVO> bookVOList = this.galleryService.booklist();
		
		log.info("bookVOList : " + bookVOList);
		
		return bookVOList;
	}
	
	// 요청URI : /gallery/updatePost
	// 방식 : post
	// 첨부 이미지를 변경함
	// 파라미터 : attachVO{"userNo" :"3","seq":"5"} + 파일객체(name은 uploadFile)
	// ajax로 요청됨
	@ResponseBody
	@PostMapping("/updatePost")
	public AttachVO updatePost(MultipartFile[] uploadFile, 
			@ModelAttribute AttachVO attachVO) {
		log.info("uploadFile : " + uploadFile + ", attachVO : " + attachVO);
		
		//업로드 폴더 설정
		String uploadFolder = 
				"C:\\eGovFrameDev-3.10.0-64bit\\workspace\\egvoProject\\src\\main\\webapp\\resources\\upload";
		
		//연월일 폴더 생성
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload Path : " + uploadPath);
		
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		// 원래 파일명
		String uploadFileName = "";
		
		//파일 배열로부터 파일을 하나씩 가져와보자.
		//MultpartFile[] uploadFile
		for(MultipartFile multpartFile : uploadFile) {
			log.info("==============================");
			log.info("upload File Name : " + multpartFile.getOriginalFilename());
			log.info("upload File size : " + multpartFile.getSize());
			log.info("==============================");
			
			uploadFileName = multpartFile.getOriginalFilename();
			
			// 같은 날 같은 이미지 업로드 시 파일 중복 방지 시작 ---------------------------------
			//java.util.UUID => 랜덤값 생성
			UUID uuid = UUID.randomUUID();
			// 원래의 파일 이름과 구분하기 위해 _ 를 붙임(sdsdadsadczxc_개똥이.jpg)
			uploadFileName = uuid.toString() + "_" +uploadFileName;
			// 같은 날 같은 이미지 업로드 시 파일 중복 방지 끝-----------------------------------
			
			//File객체 설계(복사할 대상 경로, 파일명)
			//uploadPath : C:\\eGovFrameDev-3.10.0-64bit\\workspace
			//				\\egvoProject\\src\\main\\webapp\\resources\\upload\\2022\\11\\16
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				//파일 복사 실행
				multpartFile.transferTo(saveFile);
				
				//썸네일 처리
				//이미지인지 체킹
				if(checkImageType(saveFile)) {// 이미지라면
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_"+uploadFileName)
							);
					//썸네일 생성
					Thumbnailator.createThumbnail(multpartFile.getInputStream(),
							thumbnail,100,100);
					thumbnail.close();
				}
				// ATTACH 테이블에 반영하기
				/*
				UPDATE ATTACH
				SET FILENAME ='/2022/11/16/4.png'
				WHERE USER_NO = 3 AND SEQ = 1;
				*/
				String filename  = "/" + getFolder().replace("\\", "/") + "/" + uploadFileName;
				log.info("filename : " + filename );
				
				attachVO.setFilename(filename);
				
				int result = this.galleryService.updatePost(attachVO);
				
				return attachVO;
				
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
				return null;
			}catch(IOException e) {
				log.error(e.getMessage());
				return null;
			} // end try
		} //end for
		
		return null;
	}
	
	//연월일 폴더를 만들어주기 위한 메소드
	public String getFolder() {
		//2022-11-16 형식(format) 지정
		// 간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 객체 생성( java.util 패키지)
		Date date = new Date();
		//2022-11-16 
		String str = sdf.format(date);
		
		return str.replace("_", File.separator);
	}
	
	// 이미지인지 판단, 썸네일은 이미지만 가능하므로....
	public boolean checkImageType(File file) {
		//MIME 타입 알아냄. .jpeg / .jpg의 MIME 타입 : image/jpeg
		String contentType; 
		try {
		contentType = Files.probeContentType(file.toPath());
		log.info("contentType : " + contentType);
		//image/jpeg는 image로 시작함 -> true
		return contentType.startsWith("image");
	} catch(IOException e) {
		e.printStackTrace();
	}
	//이 파일의 이미지가 아닐경우
	 return false;
	}
	
	// 이미지 삭제
	// 요청URI : /gallery/deletePost
	// 요청 파라미터 : {"userNp":"3","seq":"9"}
	// RequestBody : 요청파라미터 타입(contentType =  contentType:"application/json;charset=utf-8")
	//				 이 json 일 때 Map 또는 VO로 받음
	// ResponseBody : json 데이터로 리턴할때 사용
	@ResponseBody
	@PostMapping("/deletePost")
	public Map<String, String> deletePost(@RequestBody AttachVO attachVO) {
		log.info("attachVO : " + attachVO);
		
		
		Map<String, String> map = new HashMap<String, String>();

		//DELETE FROM ATTACH WHERE USER_NO 3 AND SEQ = 9
		int result = this.galleryService.deletePost(attachVO);
		log.info("result : " + result);
		
		map.put("result",result+"");
		
		return map;
	}
	
	  @GetMapping("/regist")     
      public String regist(Model model){
    	  
		  // 공통 약속
		  model.addAttribute("bodyTitle","이미지등록");
		  
		  
		  //forwarding
		  return "gallery/regist";
		  
      }
	  
	  /*
	   * 	요청URI : /gallery/registPost
	    	요청파라미터 : {"title":"당근마켓"}
	    	@return json 데이터
	   		방식 : post
	   */
	  @ResponseBody
	  @PostMapping("/registPost")
	  public List<BookVO> registPost(@RequestBody BookVO bookVO) {
		  log.info("bookVO : " + bookVO);
	  
		  List<BookVO> bookVOList = this.galleryService.searchPost(bookVO);
	  
		  log.info("bookVOList : " + bookVOList);
		  
		  return bookVOList;
		  
		  
	  }
	  
	  	  
	
}
