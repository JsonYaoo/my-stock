package com.jsonyao.mystock.sync.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.sync.base.bo.SyncBaseBO;
import com.jsonyao.mystock.sync.base.entity.SyncBase;
import com.jsonyao.mystock.sync.base.mapper.SyncBaseMapper;
import com.jsonyao.mystock.sync.base.service.SyncBaseService;
import com.jsonyao.mystock.sync.base.vo.SyncBaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 股票基本信息表 业务层实现类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Service
public class SyncBaseServiceImpl implements SyncBaseService {

    @Autowired
    private SyncBaseMapper syncBaseMapper;

    @Override
    public SyncBaseVO querySyncBaseByParams(SyncBaseBO syncBaseBO) {
        log.info("根据参数查询股票基本信息: syncBaseBO={}", JSONObject.toJSONString(syncBaseBO));
        Assert.notNull(syncBaseBO.getBaseId(), "baseId不能为空!");

        SyncBase syncBase = syncBaseMapper.selectByPrimaryKey(syncBaseBO.getBaseId());
        if(syncBase == null) {
            return null;
        }

        SyncBaseVO syncBaseVO = new SyncBaseVO();
        BeanUtils.copyProperties(syncBase, syncBaseVO);

        return syncBaseVO;
    }
}
