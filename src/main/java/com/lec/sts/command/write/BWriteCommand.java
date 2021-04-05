package com.lec.sts.command.write;

import com.lec.sts.beans.BWriteDTO;
import com.lec.sts.beans.IWriteDAO;
import common.C;
import org.springframework.ui.Model;

public class BWriteCommand implements BCommand {

	// 커맨드 객체(request parameter들)를 "dto" 라는 이름으로 Model 에 담아 호출됨
	@Override
	public void execute(Model model) {
		BWriteDTO dto = (BWriteDTO)model.getAttribute("dto");
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		model.addAttribute("result", dao.insert(dto));

		// auto-generated 된  uid 값 확인
		System.out.println("생성된 uid 는 " + dto.getUid());

//		model.addAttribute("result",
//				dao.insert(dto.getSubject(), dto.getContent(), dto.getName()));
	} // end execute()
} // end Command
















