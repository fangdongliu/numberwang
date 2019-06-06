package cn.fdongl.numberwangbackend.service;

import cn.fdongl.numberwangentity.entity.LogFile;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangbackend.repository.FileRepository;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Scanner;

@Service
@Slf4j
public class FileService {

    private static String STORAGE_PATH = "E:\\numberwang\\";

    @Autowired
    FileRepository fileRepository;

//    @Autowired
//    FileSystem fileSystem;

    @Value("${fs-prefix}")
    String fsPrefix;

    @Transactional(rollbackFor = Exception.class)
    public Result upload(MultipartFile multipartFile) throws IOException {
        return null;
//        LogFile file = new LogFile();
//        file.setFilename(multipartFile.getOriginalFilename());
//        fileRepository.save(file);
//
//        String filename = file.getId().toString();
//        try {
//            boolean isCover = true;
//            Path uploadPath = new Path(fsPrefix + file.getId());
//            if (fileSystem.exists(uploadPath)) {
//                log.info("file:" + file.getId() + " already exists in HDFS,将被覆盖");
//            }
//            OutputStream out = fileSystem.create(uploadPath, true);
//
//            IOUtils.copyBytes(
//                    new BufferedInputStream(multipartFile.getInputStream()),
//                    out, 4096, true
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.fail("errMsg:上传文件失败");
//        }
//        return Result.success(file);
    }

    public Result download(LogFile logFile, HttpServletResponse response) {
        return null;
//        try {
//            InputStream in = fileSystem.open(new Path(fsPrefix + logFile.getId()));
//            response.setHeader("content-type", "application/octet-stream");
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Disposition", "attachment;filename=" + logFile.getFilename());
//            byte[] buffer = new byte[1024];
//            IOUtils.copyBytes(
//                    in,
//                    response.getOutputStream(),
//                    4096, true
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.fail("errMsg:文件下载失败，无法从hdfs中获取");
//        }
//        return Result.success();
    }

    public Result top5line(LogFile logFile) {
        return  null;
//        StringBuilder result = new StringBuilder();
//        try (InputStream in = fileSystem.open(new Path(fsPrefix + logFile.getId()));
//             BufferedReader reader = new BufferedReader(new InputStreamReader(in))
//        ) {
//            for (int i = 0; i < 5 && reader.ready(); i++) {
//                result.append(reader.readLine());
//                result.append('\n');
//            }
//        } catch (IOException e) {
//            return Result.fail("errMsg:top5line:文件打开失败");
//        }
//        return Result.success(result.toString());
    }
}
