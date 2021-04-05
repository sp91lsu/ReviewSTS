package com.lec.sts.ticket;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface ITicketDAO {
	int insertCard(String userId, int buyAmount); // 카드 결제 (카드사)
	int insertTicket(String userId, int ticketCount); // 티켓 발권 (공연사)
}
