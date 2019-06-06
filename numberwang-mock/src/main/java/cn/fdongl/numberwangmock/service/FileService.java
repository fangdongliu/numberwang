package cn.fdongl.numberwangmock.service;

import cn.fdongl.numberwangentity.entity.LogFile;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangmock.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private static String STORAGE_PATH = "E:\\numberwang\\";

    @Autowired
    FileRepository fileRepository;

    @Transactional(rollbackFor = Exception.class)
    public Result upload(MultipartFile multipartFile) throws IOException {
        LogFile file = new LogFile();
        file.setFilename(multipartFile.getOriginalFilename());
        fileRepository.save(file);

        String filename = file.getId().toString();

        try {
            BufferedOutputStream out = new BufferedOutputStream
                    (new FileOutputStream
                            (new File(STORAGE_PATH + filename)));
            out.write(multipartFile.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return Result.success(file);
    }

    public Result download(LogFile logFile, HttpServletResponse response) {

        File file = new File(STORAGE_PATH + logFile.getId());
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + logFile.getFilename());
            byte[] buffer = new byte[1024];
            try (
                    FileInputStream fis = new FileInputStream(file);
                    OutputStream os = response.getOutputStream();
            ) {
                while (fis.read(buffer) != -1) {
                    os.write(buffer);
                }
                os.flush();
            } catch (IOException e) {
                return Result.fail("errMsg:fileService-65:文件打开失败");
            }
        }
        return Result.success();
    }

    public Result top5line(LogFile logFile) {
        File file = new File(STORAGE_PATH + logFile.getId());
        StringBuilder result = new StringBuilder();
        if (file.exists()) {
            try (
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                for (int i = 0; i < 5 && reader.ready(); i++) {
                    result.append(reader.readLine());
                    result.append('\n');
                }
            } catch (IOException e1) {
                return Result.fail("errMsg:download:文件打开失败");
            }
        }
        return Result.success(result.toString());
    }

}
