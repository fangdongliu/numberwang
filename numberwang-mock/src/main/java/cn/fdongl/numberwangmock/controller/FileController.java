package cn.fdongl.numberwangmock.controller;

import cn.fdongl.numberwangentity.entity.LogFile;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangmock.repository.FileRepository;
import cn.fdongl.numberwangmock.security.AppUser;
import cn.fdongl.numberwangmock.service.FileService;
import jdk.nashorn.internal.runtime.arrays.IntElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    @PostMapping(value = "upload",headers = "content-type=multipart/form-data")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.upload(file);
    }

    @GetMapping("list")
    public Result list(
            AppUser appUser,
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            String filename,
            String startDate,
            String endDate
    ) throws Exception {
        Date start = null;
        Date end = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(startDate!=null){
            start = format.parse(startDate);
        }
        if(endDate!=null){
            end = format.parse(endDate);
        }
        if(filename!=null){
            filename = '%'+filename+'%';
        }
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(filename==null){
            if(start==null||end==null){
                return Result.success(fileRepository.findByCreateBy(appUser.getUser().getId(),pageRequest));
            }else{
                return Result.success(fileRepository.findByCreateByAndCreateDateBetween(
                        appUser.getUser().getId(),
                        start,end,
                        pageRequest
                ));
            }
        }else if(start==null||end==null){
            return Result.success(fileRepository.findByCreateByAndAndFilenameLike(
                    appUser.getUser().getId(),
                    filename,
                    pageRequest
            ));
        }else{
            return Result.success(fileRepository.findByCreateByAndFilenameLikeAndCreateDateBetween(
                    appUser.getUser().getId(),
                    filename,
                    start,end,
                    pageRequest
            ));
        }
    }

    @GetMapping("top5line")
    public Result top5line(AppUser appUser,@RequestParam Long fileId) throws Exception {
        LogFile logFile = fileRepository.findByCreateByAndAndId(appUser.getUser().getId(),fileId);
        return fileService.top5line(logFile);
    }

    @GetMapping("download")
    public Result download(AppUser appUser,Long fileId,HttpServletResponse response) throws Exception {
        LogFile logFile = fileRepository.findByCreateByAndAndId(appUser.getUser().getId(),fileId);
        if(logFile==null){
            return Result.fail("errMsg:文件未找到");
        }
        return fileService.download(logFile,response);
    }

    @PutMapping("rename")
    public Result rename(@RequestParam Long fileId,@RequestParam String filename) throws Exception {
        LogFile logFile = fileRepository.getOne(fileId);
        logFile.setFilename(filename);
        fileRepository.save(logFile);
        return Result.success(logFile);
    }

    @DeleteMapping("remove")
    public Result remove(AppUser user,@RequestParam Long fileId) throws Exception {
        fileRepository.deleteLogFileByCreateByAndAndId(user.getUser().getId(),fileId);
        return Result.success();
    }

}
