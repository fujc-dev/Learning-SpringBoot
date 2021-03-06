package com.zc58s.springmvcdemo.handler;

import com.zc58s.springmvcdemo.enumeration.Sex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:18
 * springboot-mybatis
 * com.zc58s.springmvcdemo.handler
 */
@MappedJdbcTypes(JdbcType.INTEGER)  //声明JdbcType为整型
@MappedTypes(value = Sex.class) //声明JavaType为SexEnum
public class SexTypeHandler extends BaseTypeHandler<Sex> {

    /**
     * 设置非空性别参数
     *
     * @param preparedStatement
     * @param i
     * @param sexEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Sex sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sexEnum.getId());
    }

    /**
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public Sex getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return Sex.getEnumById(sex);
    }

    /**
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Sex getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return Sex.getEnumById(i);
    }

    /**
     * 通过存储过程读取性别
     *
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Sex getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return Sex.getEnumById(i);
    }
}
