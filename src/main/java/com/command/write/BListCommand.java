package com.command.write;

import com.lec.beans.BWriteDAO;
import com.lec.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.List;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BWriteDAO dao = new BWriteDAO();
		List<BWriteDTO> list = dao.select();
		model.addAttribute("list", list);
	}

}












