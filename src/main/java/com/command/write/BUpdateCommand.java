package com.command.write;

import com.lec.beans.BWriteDAO;
import com.lec.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.Map;

public class BUpdateCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		BWriteDTO dto = (BWriteDTO)map.get("dto");
		BWriteDAO dao = new BWriteDAO();
		int cnt = dao.update(dto);
		model.addAttribute("result", cnt);
	}

}















