package com.hotax.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import yygh.model.acl.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jeff 2022-04-04 14:02
 * @description:
 **/
public class TestEasyExcel {
    @Test
    public void test_writeExcel(){

        List<UserData> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("lucy"+i);
            list.add(data);
        }

        String fileName = "E:\\backup\\excel\\1.xlsx";

        EasyExcel.write(fileName, UserData.class).sheet("用户信息").doWrite(list);
    }

    @Test
    public void test_readExcel(){
        String fileName = "E:\\backup\\excel\\1.xlsx";
        EasyExcel.read(fileName, UserData.class, new ExcelListener()).sheet().doRead();
    }
}
