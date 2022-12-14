package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

public interface GalleryService {
	// 메소드 시그니처
	
	
	// 한권의 책에 대한 여러개의 이미지 목록
	public BookVO List(BookVO bookVO);
	
	// 도서 목록가져와서 select에 추가하기
	public List<BookVO> bookList();

	//변경된 사진 DB 반영하기
	public int updatePost(AttachVO attachVO);

	// 업로드 사진 삭제하기
	public int deletePost(AttachVO attachVO);

	// 도서검색
	public List<BookVO> searchPost(BookVO bookVO);

	
	// 책 별 이미지 등록하기
	public int uploadAjaxAction(List<AttachVO> attachVOList);

	//책의 이미지인 ATTACH 테이블의 다음 seq번호 가져오기
	public int getSeq(String bookId);


	


}
