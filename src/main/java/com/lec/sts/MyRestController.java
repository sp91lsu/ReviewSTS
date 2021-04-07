package com.lec.sts;

import com.lec.sts.beans.BWriteDTO;
import com.lec.sts.beans.IWriteDAO;
import common.C;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/MyRest")
public class MyRestController {
	
	// 일반 text 데이터 response
	@RequestMapping("/")
	public String helloTEXT() {
		return "Hello REST";
	}

	// JSON 데이터 <- 자바객체
	@RequestMapping("/helloJSON")
	public BWriteDTO helloJSON() {
		BWriteDTO dto =
				new BWriteDTO(100, "안녕하세요",
						"REST", "하하", 123, new Timestamp(10000));
		return dto;
	}

	// JSON 데이터 <- 자바객체 List
	@RequestMapping("/listJSON")
	public List<BWriteDTO> listJSON(){
		return C.sqlSession.getMapper(IWriteDAO.class).select();
	}

	// JSON 데이터 <- 자바배열
	@RequestMapping("/arrJSON")
	public BWriteDTO [] arrJSON() {
		List<BWriteDTO> list = C.sqlSession.getMapper(IWriteDAO.class).select();
		BWriteDTO[] arr = new BWriteDTO[list.size()];
		return list.toArray(arr);
	}

	// JSON 데이터 <- Map<k, v>
	@RequestMapping("/mapJSON")
	public Map<Integer, BWriteDTO> mapJSON(){
		List<BWriteDTO> list = C.sqlSession.getMapper(IWriteDAO.class).select();

		Map<Integer, BWriteDTO> map = new HashMap<Integer, BWriteDTO>();
//		for(BWriteDTO dto : list) {
//			map.put(dto.getUid(), dto);
//		}

		list.forEach(dto -> map.put(dto.getUid(), dto));

		return map;
	}

	@RequestMapping(value = "/read/{uid}")
	public ResponseEntity<BWriteDTO> read(@PathVariable("uid") int uid) {
		List<BWriteDTO> list = C.sqlSession.getMapper(IWriteDAO.class).selectByUid(uid);
		// 성공
		if(list.size() > 0)
			return new ResponseEntity<BWriteDTO>(list.get(0), HttpStatus.OK);

		// 실패
		return new ResponseEntity(HttpStatus.NOT_FOUND); // 404
	}
}

















