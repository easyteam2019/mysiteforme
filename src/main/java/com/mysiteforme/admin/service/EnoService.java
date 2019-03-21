package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Eno;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客标签 服务类
 * </p>
 *
 * @author wangl
 * @since 2018-01-17
 */
public interface EnoService extends IService<Eno> {

    /**
     * 根据标签名称获取数量
     * @param name 标签名称
     * @return
     */
    Integer getCountByName(String name);

    /**
     * 新增标签
     * @param eno
     */
    void saveOrUpdateEno(Eno eno);

    /**
     * 获取所有标签
     * @return
     */
    List<Eno> listAll();

    /**
     * 获取栏目对应的文章的所有标签
     * @param eno 栏目ID
     * @return
     */
    List<Eno> getEnoByNo(Long eno);



    void deleteThisTag(Long id);

    Page<Eno> selectEnoPage(Map<String, Object> map, Page<Eno> page);

    List<Eno> selectEnoPage(Map<String, Object> map);
}
