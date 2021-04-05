package com.lec.sts.command.write;

import com.lec.sts.beans.IWriteDAO;
import common.C;
import org.springframework.ui.Model;

public class BSelectCommand implements BCommand {

	@Override
	public void execute(Model model) {
		int uid = (Integer)model.getAttribute("uid");
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		model.addAttribute("list", dao.selectByUid(uid));
	}

}















