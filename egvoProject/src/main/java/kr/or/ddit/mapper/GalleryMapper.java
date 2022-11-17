package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

public interface GalleryMapper {

	// 한권의 책에 대한 여러개의 이미지 목록
	public BookVO list(BookVO bookVO);
	
	// 도서 목록가져와서 select에 추가하기
	public List<BookVO> booklist();
	
	//변경된 사진 DB 반영하기
	public int updatePost(AttachVO attachVO);
	
	//업로드 사진 삭제하기
	public int deletePost(AttachVO attachVO);
	
	//도서 검색
	public List<BookVO> searchPost(BookVO bookVO);
	
}
