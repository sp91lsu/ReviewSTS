package com.study.sts;

import com.study.beans.WriteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

	@RequestMapping("write.do")
	public String write() {
		
		return "board/write";
	}
	
	@RequestMapping("writeOk.do")
	public String writeOk(@ModelAttribute("w") @Valid WriteDTO dto,
			BindingResult result) {
		System.out.println("writeOk():" + dto);
		
//		System.out.println("validate 전"); showErrors(result);
		
		String page = "board/writeOk";
		
		// validate 객체 생성
		BoardValidator validator = new BoardValidator();
		validator.validate(dto, result);
		
		System.out.println("validate 후"); showErrors(result);
		
		if(result.hasErrors()) { // 에러 있다면
			page = "board/write";  // 원래 폼으로 돌아가기
		}
		
		
		return page;
	}
	
	// 에러 출력 도우미
	public void showErrors(Errors errors) {
		if(errors.hasErrors()) {
			System.out.println("에러개수: " + errors.getErrorCount());
			System.out.println("\t[field]\t|[code] ");
			List<FieldError> errList = errors.getFieldErrors();
			for(FieldError err : errList) {
				System.out.println("\t" + err.getField() + "\t|" +
											err.getCode());	
			}
		} else {
			System.out.println("에러 없슴");
		}
	}
	
	// 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩할때 '검증'을 수행하는 객체 지정
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new BoardValidator());
	}
	
	
}
















