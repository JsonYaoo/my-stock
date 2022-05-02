package com.jsonyao.mystock.sync.base.controller;

import com.jsonyao.mystock.sync.base.bo.SyncBaseBO;
import com.jsonyao.mystock.sync.base.service.SyncBaseService;
import com.jsonyao.mystock.sync.base.vo.SyncBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 股票基本信息表 前端控制器
 *
 * @author yaocs2
 * @since 20220502
 */
@RestController
@RequestMapping("/sync/base")
public class SyncBaseController {

    @Autowired
    private SyncBaseService syncBaseService;

    @PostMapping("/test")
    public void test() {
        System.out.println("test");
    }

    @PostMapping("/querySyncBaseByParams")
    public SyncBaseVO querySyncBaseByParams(@RequestBody SyncBaseBO syncBaseBO) {
        return syncBaseService.querySyncBaseByParams(syncBaseBO);
    }
}
