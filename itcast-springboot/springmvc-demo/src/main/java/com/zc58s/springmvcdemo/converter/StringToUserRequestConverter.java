package com.zc58s.springmvcdemo.converter;

import com.zc58s.springmvcdemo.request.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/6 16:47
 * springmvc-demo
 * com.zc58s.springmvcdemo.converter
 */
@Component
public class StringToUserRequestConverter implements Converter<String, UserRequest> {

    /**
     * 将一串协议字符串转换为{@link UserRequest}对象
     *
     * @param s
     * @return
     */
    @Override
    public UserRequest convert(String s) {
        try {
            String[] array = s.split("-");
            UserRequest request = new UserRequest();
            request.setId(Integer.parseInt(array[0]));
            request.setUsername(array[1]);
            request.setNote(array[2]);
            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
