package com.lec.sts.beans;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

@MapperScan
public interface IWriteDAO {
    public ArrayList<BWriteDTO> select();
    public int insert(BWriteDTO dto);
    public List<BWriteDTO> selectByUid(int uid);
    public int update(int uid, @Param("a") BWriteDTO dto);
    public int deleteByUid(int uid);
    public int incViewCnt(int uid);
    public BWriteDTO searchBySubject(String subject);
}


