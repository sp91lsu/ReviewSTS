package com.command.write;

import com.lec.beans.BWriteDAO;
import com.lec.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class BViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int uid = (Integer)map.get("uid");
		
		// byte short int long float double char boolean
		// Byte Short Integer Long Float Double Character Boolean
		//Integer i = 100;
		
		BWriteDAO dao = new BWriteDAO();
		List<BWriteDTO> list = dao.readByUid(uid);
		model.addAttribute("list", list);
	}

}














