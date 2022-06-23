package com.kh.mvc.member.model.service;

import static com.kh.mvc.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.dto.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	/**
	 * DQL 요청 - service
	 * 1. Connection 객체 생성
	 * 2. Dao 요청 & Connection 전달
	 * 3. Connection 반환(close)
	 */
	public Member findById(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findById(conn, memberId);
		close(conn);
		
		return member;
	}
	
	/**
	 * DML 요청 - service
	 * 1. Connection 객체 생성
	 * 2. Dao 요청 & Connection 전달
	 * 3. 트랜잭션 처리(정상처리 commit, 예외발생 rollback)
	 * 4. Connection 반환(close)
	 */
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;	// controller에 예외 던짐
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
}