package com.kggs.electronicpatrol;

import com.kggs.electronicpatrol.exception.ExcelLoadException;
import com.kggs.electronicpatrol.factory.ElectronicPatrolFactory;
import com.kggs.electronicpatrol.pojo.Rule;
import com.kggs.electronicpatrol.service.IExcelFileLoadService;
import com.kggs.electronicpatrol.service.impl.ExcelFileLoadServiceImp;
import com.kggs.electronicpatrol.vo.ExcelInputStreamRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ElectronicPatrolApplication {


    public static void main(String[] args) throws FileNotFoundException, ExcelLoadException {
        SpringApplication.run(ElectronicPatrolApplication.class, args);
        String fileName = "E:\\MyProjects\\Learning-SpringBoot\\Electronic-Patrol\\高铁站巡更系统记录表.xls";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        Map<String, List<Rule>> map = new HashMap<>();
        List<Rule> rules = new ArrayList<>();
        //rules.add(new Rule("18:30", "19:00"));
        //rules.add(new Rule("23:00", "23:05"));
        //rules.add(new Rule("12:55", "11:00"));
        rules.add(new Rule("23:55", "00:10"));
        map.put("008DBF6D", rules);
        new ElectronicPatrolFactory().AddRules(map).Load(0, inputStream).Filter();
    }

}
