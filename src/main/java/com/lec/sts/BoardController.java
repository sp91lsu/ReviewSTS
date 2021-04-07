package com.lec.sts;

import com.lec.sts.beans.BWriteDTO;
import com.lec.sts.command.write.*;
import common.C;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	// MyBatis
	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		C.sqlSession = sqlSession;
	}

	@RequestMapping("/list.do")
	public String list(Model model) {
		new BListCommand().execute(model);
		return "board/list";
	}
	
	@RequestMapping("/write.do")
	public String write(Model model){
		return "board/write";
	}
	
	@RequestMapping(value="/writeOk.do", method = RequestMethod.POST)
	public String writeOk(BWriteDTO dto, Model model) {
		model.addAttribute("dto", dto);
		new BWriteCommand().execute(model);
		return "board/writeOk";
	}
	
	@RequestMapping("/view.do")
	public String view(int uid, Model model) {
		model.addAttribute("uid", uid);
		new BViewCommand().execute(model);
		return "board/view";
	}
	
	@RequestMapping("/update.do")
	public String update(int uid, Model model) {
		model.addAttribute("uid", uid);
		new BSelectCommand().execute(model);		
		return "board/update";
	}
	
	@RequestMapping(value="/updateOk.do", method= RequestMethod.POST)
	public String updateOk(BWriteDTO dto, Model model) {
		model.addAttribute("dto", dto);
		new BUpdateCommand().execute(model);
		return "board/updateOk";
	}
	
	@RequestMapping(value="/deleteOk.do")
	public String deleteOk(int uid, Model model) {
		model.addAttribute("uid", uid);
		new BDeleteCommand().execute(model);
		return "board/deleteOk";
	}

	// REST 게시판 작성 연습
	@RequestMapping("/rest")
	public void rest() {}   //   /board/rest.jsp
}

















