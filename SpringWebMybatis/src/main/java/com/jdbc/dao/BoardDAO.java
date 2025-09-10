package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;

import com.jdbc.dto.BoardDTO;

public class BoardDAO {
	
	//의존성 주입
	private SqlSessionTemplate sessionTemplate;
	
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;		
	}
		
	//num의 최대값
	public int getMaxNum() {
		
		int maxNum = 0;
		
		maxNum = sessionTemplate.selectOne("com.board.maxNum");
		
		return maxNum;
		
	}
	
	//입력
	public void insertData(BoardDTO dto) {
		
		sessionTemplate.insert("com.board.insertData",dto);
		
	}
	
	//전체데이터의 갯수
	public int getDataCount(String searchKey,String searchValue) {
				
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		int totalCount = 
		sessionTemplate.selectOne("com.board.getDataCount",params);
				
		return totalCount;
		
	}	
	
	
	//표시할 데이터
	public List<BoardDTO> getLists(int start,int end,
			String searchKey,String searchValue){
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("start", start);
		params.put("end",end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		List<BoardDTO> lists = 
			sessionTemplate.selectList("com.board.getLists",params);
		
		return lists;
	}
	
	//조회수 증가
	public void updateHitCount(int num) {
		
		sessionTemplate.update("com.board.updateHitCount",num);
		
	}
	
	//num으로 조회한 한개의 데이터
	public BoardDTO getReadData(int num) {
		
		BoardDTO dto = 
			sessionTemplate.selectOne("com.board.getReadData",num);
		
		return dto;		
		
	}
	
	//수정
	public void updateData(BoardDTO dto) {
		
		sessionTemplate.update("com.board.updateData",dto);
		
	}	

	//삭제
	public void deleteData(int num) {
		
		sessionTemplate.delete("com.board.deleteData",num);
	}
	
	
}













































