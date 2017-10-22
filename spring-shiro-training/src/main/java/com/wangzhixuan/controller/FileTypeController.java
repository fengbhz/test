package com.wangzhixuan.controller;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.DateUtil;
import com.wangzhixuan.commons.utils.FileUnZip;
import com.wangzhixuan.commons.utils.PropKit;
import com.wangzhixuan.model.Files;
import com.wangzhixuan.model.FilesType;
import com.wangzhixuan.service.IFileService;
import com.wangzhixuan.service.IFilesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 文件原型分类
 */


@Controller
@RequestMapping("/filetype")
public class FileTypeController extends BaseController {

    @Autowired
    private IFilesTypeService filesTypeService;


@GetMapping("/view")
 public String view(){

     return "admin/filetype/fileType";
 }

    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        filesTypeService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/filetype/fileTypeAdd";
    }
    /**
     * 添加权限
     *
     * @param files
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid FilesType files, HttpServletRequest request, ModelMap model) {
        files.setCreattime(DateUtil.dateTimeNow());
        filesTypeService.insert(files);
        return renderSuccess("添加成功！");
    }


    /**
     * 编辑权限页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        FilesType files = filesTypeService.selectById(id);
        model.addAttribute("files", files);
        return "admin/filetype/fileTypeEdit";
    }

    /**
     * 删除权限
     *
     * @param files
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(@Valid FilesType files) {
        files.setCreattime(DateUtil.dateTimeNow());
        filesTypeService.updateById(files);
        return renderSuccess("编辑成功！");
    }


    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        filesTypeService.deleteById(id);
        return renderSuccess("删除成功！");
    }



}
