package com.wangzhixuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.mapper.FilesMapper;
import com.wangzhixuan.mapper.FilesTypeMapper;
import com.wangzhixuan.model.Files;
import com.wangzhixuan.model.FilesType;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.IFileService;
import com.wangzhixuan.service.IFilesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/8/22 0022.
 */
@Service
public class FilesTypeServiceImpl extends ServiceImpl<FilesTypeMapper, FilesType> implements IFilesTypeService{
    @Autowired
    private FilesTypeMapper filestypeMapper ;
    ;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<FilesType> page = new Page<FilesType>(pageInfo.getNowpage(), pageInfo.getSize());
        EntityWrapper<FilesType> wrapper = new EntityWrapper<FilesType>();
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        selectPage(page, wrapper);
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
    }


}


