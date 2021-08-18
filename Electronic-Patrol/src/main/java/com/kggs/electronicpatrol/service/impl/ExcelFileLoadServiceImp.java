package com.kggs.electronicpatrol.service.impl;

import com.kggs.electronicpatrol.annotation.DefinitionName;
import com.kggs.electronicpatrol.exception.ExcelLoadException;
import com.kggs.electronicpatrol.pojo.Flow;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.kggs.electronicpatrol.service.base.ExcelFileLoadBase;
import com.kggs.electronicpatrol.vo.ExcelInputStreamRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 16:50
 */
@Service
public class ExcelFileLoadServiceImp extends ExcelFileLoadBase {

    @Override
    public List<Flow> Load(ExcelInputStreamRequest request) throws ExcelLoadException {
        List<Flow> containers = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(request.getInputStream());
        } catch (Exception ex) {
            try {
                workbook = new XSSFWorkbook(request.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = workbook.getSheetAt(0);
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        //遍历表数据
        for (int rowIndex = request.getRowIndex(); rowIndex < totalRowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null || !this.CheckRowValidity(row)) continue;
            System.out.println("==========> 行索引：" + rowIndex + " Row Begin");
            //构建行数据对象，并返回FlowEntity对象实例，并将该实例，写入到集合中
            Flow entity = this.BuilderObject(row);
            if (entity != null) {
                containers.add(entity);
                System.out.println("==========>" + entity.toString());
            }
            System.out.println("==========> 行索引：" + rowIndex + " Row End");
            System.out.println("");
        }
        return containers;
    }

    /**
     * 构建行对象{@link Flow}
     *
     * @param row
     * @return
     */
    private Flow BuilderObject(Row row) {
        Boolean isHasVal = false;
        Flow entity = new Flow();
        Field[] fields = Flow.class.getDeclaredFields();
        for (Field f : fields) {
            //获取Field字段的注解对象DefinitionName
            boolean annotationDefinitionName = f.isAnnotationPresent(DefinitionName.class);
            if (annotationDefinitionName) {
                DefinitionName definitionName = f.getAnnotation(DefinitionName.class);
                int cellIndex = this.ExcelColStrToNum(definitionName.cellIndex());
                Cell cell = row.getCell(cellIndex);
                String cellVal = this.GetCell(cell);
                if (!cellVal.equals(definitionName.cellName())) {
                    //将读取到的值，存储到FlowEntity中
                    try {
                        PropertyDescriptor propDesc = new PropertyDescriptor(f.getName(), Flow.class);
                        Method writeMethod = propDesc.getWriteMethod();
                        writeMethod.invoke(entity, this.GetValue(definitionName, cellVal));
                        isHasVal = true;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return isHasVal ? entity : null;
    }

    /**
     * 设置属性值
     *
     * @param definitionName 属性定义对象
     * @param propertyName   属性名称
     * @param cellVal        Excel的Cell值
     * @param entity
     */
    private void SetValue(DefinitionName definitionName, String propertyName, String cellVal, Flow entity) {
        try {
            PropertyDescriptor propDesc = new PropertyDescriptor(propertyName, Flow.class);
            Method writeMethod = propDesc.getWriteMethod();
            //当存储的类型为日期类型时，需要将数据格式化位日期类型
            if (definitionName.dataType().equals(Date.class)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = sdf.parse(cellVal);
                    writeMethod.invoke(entity, date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                writeMethod.invoke(entity, cellVal);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据数据模型中定义的类型，获取数据
     *
     * @param definitionName
     * @param cellVal
     * @return
     */
    private Object GetValue(DefinitionName definitionName, String cellVal) {
        //当存储的类型为日期类型时，需要将数据格式化位日期类型
        if (definitionName.dataType().equals(Date.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(cellVal);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return cellVal;
    }

    /**
     * 监测行的有效性，当人员卡号列为空的时候，掘弃这一行数据
     *
     * @param row {@link Row}
     * @return
     */
    private Boolean CheckRowValidity(Row row) {
        Cell cell = row.getCell(0);
        if (StringUtils.isEmpty(this.GetCell(cell))) {
            return false;
        }
        return true;
    }
}

