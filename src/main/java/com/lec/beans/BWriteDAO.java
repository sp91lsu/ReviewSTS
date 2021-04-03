package com.lec.beans;

import common.C;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BWriteDAO {
	
	JdbcTemplate template;
	
	public BWriteDAO() {
		this.template = C.template;
	}
	
	// 전체 SELECT
	public List<com.lec.beans.BWriteDTO> select(){
		return template.query(C.SQL_WRITE_SELECT, 
				new BeanPropertyRowMapper<com.lec.beans.BWriteDTO>(com.lec.beans.BWriteDTO.class)
				);
	}
	
	// 새 글 작성 (INSERT)
	public int insert(com.lec.beans.BWriteDTO dto) {
		
		// 1. update() + PreparedStatementSetter() 사용
//		return
//		this.template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, dto.getSubject());
//				ps.setString(2, dto.getContent());
//				ps.setString(3, dto.getName());				
//			}
//		});
		
		// 2. update() + PreparedStatementCreator() 사용
		return 
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement ps = con.prepareStatement(C.SQL_WRITE_INSERT);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setString(3, dto.getName());
				return ps;
			}
		});
		
	}

	public List<com.lec.beans.BWriteDTO> readByUid(int uid) {
		//BWriteDTO dto = null;
		
		// 조회수 증가
		this.template.update(C.SQL_WRITE_INC_VIEWCNT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		});
		
		
		// uid 의 글 읽기
		List<com.lec.beans.BWriteDTO> list =
		this.template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		}, new BeanPropertyRowMapper<com.lec.beans.BWriteDTO>(com.lec.beans.BWriteDTO.class));
		
		return list;
	}

	public List<com.lec.beans.BWriteDTO> selectByUid(int uid) {
		
		List<com.lec.beans.BWriteDTO> list =
				template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, uid);						
					}
				}, new BeanPropertyRowMapper<com.lec.beans.BWriteDTO>(com.lec.beans.BWriteDTO.class));
		
		return list;
	}

	public int update(com.lec.beans.BWriteDTO dto) {
		int cnt = 0;
		
		cnt =
			template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, dto.getSubject());
					ps.setString(2, dto.getContent());
					ps.setInt(3, dto.getUid());
					
				}
			} );
		
		
		return cnt;
	}

	public int deleteByUid(int uid) {
		int cnt = 0;
		
		cnt =
			template.update(C.SQL_WRITE_DELETE_BY_UID, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, uid);					
				}
			});
		
		return cnt;
	}
	
	
}



















