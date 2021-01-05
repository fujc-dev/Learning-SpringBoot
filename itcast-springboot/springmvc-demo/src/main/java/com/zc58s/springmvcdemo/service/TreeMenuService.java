package com.zc58s.springmvcdemo.service;

import com.zc58s.springmvcdemo.entity.TreeMenu;

import java.util.List;

/**
 * 树形菜单(TreeMenu)表服务接口
 *
 * @author fjc.dane@gmail.com
 * @createtime 2021-01-05 11:50:21
 */
public interface TreeMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    TreeMenu queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TreeMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param treeMenu 实例对象
     * @return 实例对象
     */
    TreeMenu insert(TreeMenu treeMenu);

    /**
     * 修改数据
     *
     * @param treeMenu 实例对象
     * @return 实例对象
     */
    TreeMenu update(TreeMenu treeMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

}