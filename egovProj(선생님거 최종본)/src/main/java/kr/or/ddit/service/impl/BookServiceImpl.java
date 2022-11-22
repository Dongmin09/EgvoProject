package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BookMapper;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bookMapper;
	
	//도서 목록
	@Override
	public List<BookVO> list() {
		return this.bookMapper.list();
	}
	
	//도서 상세(1행)
	@Override
	public BookVO detail(int bookId) {
		return this.bookMapper.detail(bookId);
	}
	
	//도서 수정
	@Override
	public int updatePost(BookVO bookVO) {
//		return this.bookMapper.updatePost(bookVO);
		log.info("before bookVO : " + bookVO);
		//BookVO [bookId=2, title=검은개똥이2, category=소설2, 
		//price=12000, insertDate=Fri Nov 11 00:00:00 KST 2022]
		//merge into문 사용		
		int result = this.bookMapper.insertPost(bookVO);
		//bookId를 확인해보자
		//BookVO [bookId=15, title=검은개똥이2, category=소설2, 
		//price=12000, insertDate=Fri Nov 11 00:00:00 KST 2022]
		log.info("after bookVO : " + bookVO);
		
		return result;
	}
	
	//도서 입력
	@Override
	public int insertPost(BookVO bookVO) {
		//BookVO [bookId=0, title=7번방의 개똥이, category=영화, price=12000, insertDate=null]
		return this.bookMapper.insertPost(bookVO);
	}
}









