package com.zc58s.springbootmybatis.handler;

import com.zc58s.springbootmybatis.enumeration.SexEnum;
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
 * com.zc58s.springbootmybatis.handler
 */
@MappedJdbcTypes(JdbcType.INTEGER)  //声明JdbcType为整型
@MappedTypes(value = SexEnum.class) //声明JavaType为SexEnum
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

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
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sexEnum.getId());
    }

    /**
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(i);
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
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(i);
    }
}
