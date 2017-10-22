package com.wangzhixuan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.Files;
import com.wangzhixuan.model.FilesType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
public interface FilesTypeMapper extends BaseMapper<FilesType> {

    List<Map<String, Object>> selectFilesTypePage(Pagination page, Map<String, Object> params);


}
