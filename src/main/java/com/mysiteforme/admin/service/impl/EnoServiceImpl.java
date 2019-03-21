package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.EnoDao;
import com.mysiteforme.admin.entity.Eno;
import com.mysiteforme.admin.service.EnoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客标签 服务实现类
 * </p>
 *
 * @author ht
 * @since 2018-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EnoServiceImpl extends ServiceImpl<EnoDao, Eno> implements EnoService {

    @Override
    public Integer getCountByName(String name) {
        return null;
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateEno(Eno eno) {
        insertOrUpdate(eno);
    }

    @Override
    public List<Eno> listAll() {
        return null;
    }

    @Override
    public List<Eno> getEnoByNo(Long eno) {
        return null;
    }

    @Override
    public void deleteThisTag(Long id) {

    }

    @Override
    public Page<Eno> selectEnoPage(Map<String, Object> map, Page<Eno> page) {


        return null;
    }

    @Override
    public List<Eno> selectEnoPage(Map<String, Object> map) {
        return null;
    }
}
