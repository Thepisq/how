package com.liushao.guangli.service;

import java.util.List;

/**
 * 通用的Service层接口
 *
 * @auther huangshen
 */
public interface BaseService<T> {

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    List<T> findById(Integer id);

    /**
     * 添加
     *
     * @param t
     */
    int create(T t);

    /**
     * 删除（批量）
     *
     * @param ids
     */
    int delete(Integer... ids);

    /**
     * 修改
     *
     * @param t
     */
    int update(T t);
}
