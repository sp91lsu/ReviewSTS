package com.lec.sts.command.write;

import com.lec.sts.beans.BWriteDAO;
import com.lec.sts.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class BSelectCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int uid = (Integer)map.get("uid");
		BWriteDAO dao = new BWriteDAO();
		List<BWriteDTO> list = dao.selectByUid(uid);
		model.addAttribute("list", list);
	}

}















