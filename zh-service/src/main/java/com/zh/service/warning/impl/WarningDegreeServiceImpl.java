package com.zh.service.warning.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.contants.RedisContant;
import com.zh.entity.warning.WarningDegree;
import com.zh.mapper.warning.WarningDegreeMapper;
import com.zh.service.warning.WarningDegreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 预警等级表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Service
public class WarningDegreeServiceImpl extends ServiceImpl<WarningDegreeMapper, WarningDegree> implements WarningDegreeService {

    @Resource
    WarningDegreeMapper warningDegreeMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public IPage<WarningDegree> queryPage(Page<WarningDegree> page) {
        return warningDegreeMapper.getWarningDegreeByPage(page, null);
    }

    @Override
    @Cacheable(cacheNames = RedisContant.WARNING_DEGREE, key = "#id")
    public WarningDegree getById(Long id) {
        WarningDegree warningDegree = warningDegreeMapper.selectById(id);
        return warningDegree;
    }
}
