package com.zc58s.springmvcdemo.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 树形菜单(TreeMenu)实体类
 *
 * @author fjc.dane@gmail.com
 * @createtime 2021-01-05 11:50:21
 */
@Alias("tree_menu")
public class TreeMenu implements Serializable {
    private static final long serialVersionUID = -17951832852245135L;
    /**
     * 当前菜单ID
     */
    private Long menuId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 当前菜单的父菜单 ID
     */
    private Long parentMenuId;
    /**
     * 当前菜单的层级
     */
    private Integer meunLevel;
    /**
     * 排序
     */
    private Integer sort;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getMeunLevel() {
        return meunLevel;
    }

    public void setMeunLevel(Integer meunLevel) {
        this.meunLevel = meunLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}