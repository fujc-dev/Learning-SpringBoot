package com.zc58s.springmvcdemo.service.impl;


import com.zc58s.springmvcdemo.dao.TreeMenuRepository;
import com.zc58s.springmvcdemo.entity.TreeMenu;
import com.zc58s.springmvcdemo.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 树形菜单(TreeMenu)表服务实现类
 *
 * @author fjc.dane@gmail.com
 * @createtime 2021-01-05 11:50:21
 */
@Service()
public class TreeMenuServiceImpl implements TreeMenuService {

    private final TreeMenuRepository repository;

    @Autowired
    public TreeMenuServiceImpl(TreeMenuRepository repository) {
        this.repository = repository;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public TreeMenu queryById(Long menuId) {
        return this.repository.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TreeMenu> queryAllByLimit(int offset, int limit) {
        return this.repository.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param treeMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TreeMenu insert(TreeMenu treeMenu) {
        this.repository.insert(treeMenu);
        return treeMenu;
    }

    /**
     * 修改数据
     *
     * @param treeMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TreeMenu update(TreeMenu treeMenu) {
        this.repository.update(treeMenu);
        return this.queryById(treeMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.repository.deleteById(menuId) > 0;
    }
}