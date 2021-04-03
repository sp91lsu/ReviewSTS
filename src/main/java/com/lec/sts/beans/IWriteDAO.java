package com.lec.sts.beans;

import java.util.ArrayList;

public interface IWriteDAO {
    public ArrayList<BWriteDTO> select();
    public int insert(final BWriteDTO dto);
    public BWriteDTO readByUid(int uid);
    public BWriteDTO selectByUid(int uid);
    public int update(BWriteDTO dto);
    public int deleteByUid(int uid);
}


