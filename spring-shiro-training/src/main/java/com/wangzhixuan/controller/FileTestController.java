package com.wangzhixuan.controller;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.ueditor.ActionConfig;
import com.wangzhixuan.commons.ueditor.define.AppInfo;
import com.wangzhixuan.commons.ueditor.define.BaseState;
import com.wangzhixuan.commons.ueditor.define.FileType;
import com.wangzhixuan.commons.utils.FileUnZip;
import com.wangzhixuan.commons.utils.PropKit;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.Files;
import com.wangzhixuan.model.FilesType;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.model.vo.FilesVo;
import com.wangzhixuan.service.IFileService;
import com.wangzhixuan.service.IFilesTypeService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by Administrator on 2017/8/22 0022.
 */


@Controller
@RequestMapping("/file")
public class FileTestController extends BaseController {

    @Autowired
    private IFileService filesService;

    @Autowired
    private IFilesTypeService filesTypeService;


@GetMapping("/view")
 public String view(Model model){
    getType(model);
     return "admin/file/file";
 }

    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(FilesVo filesVo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(filesVo.getName())) {
            condition.put("name", filesVo.getName());
        }
        if (StringUtils.isNotBlank(filesVo.getAccount())) {
            condition.put("accout", filesVo.getAccount());
        }
        if (StringUtils.isNotBlank(filesVo.getType())){
            condition.put("type", filesVo.getType());
        }
        pageInfo.setCondition(condition);
        filesService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
        getType(model);
            return "admin/file/fileAdd";
    }
    /**
     * 添加权限
     *
     * @param files
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Files files,
                      @RequestParam(value = "file", required = false) MultipartFile file,
                      HttpServletRequest request, ModelMap model) {
        System.out.println("开始");
        FileChannel fc= null;
        String uid=  UUID.randomUUID().toString().replace("-","");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        System.out.println(path);
        File filepath = new File(path+"/"+uid);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        File targetFile = new File( path+"/"+uid, fileName);
        //保存
        try {
            file.transferTo(targetFile);
            // 解压
            FileInputStream fis= new FileInputStream(targetFile);
            fc= fis.getChannel();

            model.addAttribute("file_size",(double)(fc.size()/1024/1024)+"M");
            model.addAttribute("uid",uid);
            files.setFilesize((double)(fc.size()/1024/1024)+"M");
            fis.close();
            fc.close();
            String newFile= FileUnZip.zipToFile(path+"/"+uid+"/"+fileName,path+"/"+uid);
            files.setUid(uid);
            PropKit.use("config/application.properties");
            String url= PropKit.get("httpUrl")+"file/v?uid="+uid;
            String _path="upload/"+uid+"/"+newFile;
            files.setPath(_path);
            files.setUrl(url);
            files.setCreattime(new Date());

        filesService.insert(files);
          } catch (Exception e) {
            e.printStackTrace();
        }
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
        Files files = filesService.selectById(id);
        model.addAttribute("files", files);
        getType(model);
        return "admin/file/fileEdit";
    }

    /**
     * 删除权限
     *
     * @param files
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Files files) {
        filesService.updateById(files);
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
        filesService.deleteById(id);
        return renderSuccess("删除成功！");
    }


    @GetMapping("/v")
    public String v(String uid){
        System.out.println("进入跳转方法");
        String url="";
        Map map=new HashMap<>();
            map.put("uid",uid);
        List<Files> list =    filesService.selectByMap(map);
        if (null!=list&&list.size()>0){
           for (Files files:list){
                 url=files.getPath()+"";
           }
        }
       try {
           url = java.net.URLEncoder.encode(url.trim(),"UTF-8");
           url = URLDecoder.decode(url,"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/"+url+"/";

    }

}
