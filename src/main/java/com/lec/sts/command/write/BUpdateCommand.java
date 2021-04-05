package com.lec.sts.command.write;

import com.lec.sts.beans.BWriteDTO;
import com.lec.sts.beans.IWriteDAO;
import common.C;
import org.springframework.ui.Model;

public class BUpdateCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BWriteDTO dto = (BWriteDTO)model.getAttribute("dto");
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		model.addAttribute("result", dao.update(dto.getUid(),dto));
	}

}















