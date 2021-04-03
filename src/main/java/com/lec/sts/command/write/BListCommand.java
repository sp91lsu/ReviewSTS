package com.lec.sts.command.write;

import common.C;
import org.springframework.ui.Model;
import com.lec.sts.beans.IWriteDAO;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		model.addAttribute("list",dao.select());
	}

}












