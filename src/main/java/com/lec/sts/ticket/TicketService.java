package com.lec.sts.ticket;

import common.C;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

// 트랜잭션 정의!
public class TicketService {
	
	// MyBatis
	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		C.sqlSession = sqlSession;
	}
	
	// TransactionTemplate 사용
	TransactionTemplate transactionTemplate;

	@Autowired
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public void buyTicket(final TicketDTO dto) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				ITicketDAO dao = C.sqlSession.getMapper(ITicketDAO.class);
				dao.insertCard(dto.getUserId(), dto.getTicketCount());
				dao.insertTicket(dto.getUserId(), dto.getTicketCount());				
			}
		});
		
	}
	
}





