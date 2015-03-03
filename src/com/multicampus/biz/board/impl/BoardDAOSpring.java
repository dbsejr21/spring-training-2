package com.multicampus.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.multicampus.biz.board.BoardVO;

// DAO(Data Access Object)
@Component
public class BoardDAOSpring {

	@Autowired
	JdbcTemplate spring;

	// SQL 명령어들
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	// 글 등록
	public void insertBoard(BoardVO vo) {
		spring.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(),
				vo.getContent());
	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		spring.update(BOARD_DELETE, vo.getSeq());
	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {

		// queryForObject() 에 넘길 아규먼트 배열, BOARD_GET 쿼리 날릴 때 ? 가 되는 부분 넘겨줘야해서.
		Object args[] = { vo.getSeq() };

		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		return spring.query(BOARD_LIST, new BoardRowMapper());
	}

	class BoardRowMapper implements RowMapper<BoardVO> {
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println(rowNum);
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
			return board;
		}
	}
}
