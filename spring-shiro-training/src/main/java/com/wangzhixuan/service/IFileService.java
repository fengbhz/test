package com.wangzhixuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.Files;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
public interface IFileService extends IService<Files> {

    void selectDataGrid(PageInfo pageInfo);
}
