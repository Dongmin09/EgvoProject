package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface BookService {
	// 메소드 시그니처
	// 도서목록
	public List<BookVO> list();

	// 도서 상세
	public BookVO detail(int bookId);

	// 수정하기
	public int updatePost(BookVO bookVO);


	// 도서 등록
	public int insertPost(BookVO bookVO);
	

}