package com.wangzhixuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.Files;
import com.wangzhixuan.model.FilesType;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
public interface IFilesTypeService extends IService<FilesType> {

    void selectDataGrid(PageInfo pageInfo);
}
