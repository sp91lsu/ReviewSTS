package com.command.write;

import com.lec.beans.BWriteDAO;
import org.springframework.ui.Model;

import java.util.Map;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int uid = (Integer)map.get("uid");
		BWriteDAO dao = new BWriteDAO();
		int cnt = dao.deleteByUid(uid);
		model.addAttribute("result", cnt);
		
		//model.addAttribute("result", new BWriteDAO().deleteByUid((Integer)(model.asMap().get("uid"))));
	}

}
















