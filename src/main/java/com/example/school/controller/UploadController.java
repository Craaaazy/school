package com.example.school.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
public class UploadController {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File("C:\\Users\\Ning\\Documents\\Temp\\" + saveFileName);

            Cloudinary cloudinary = new Cloudinary();
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

            System.out.println(uploadResult);

            FileOutputStream out = new FileOutputStream(saveFile);
            out.write(file.getBytes());
            out.flush();
            out.close();

            return "upload success";
        }else {
            return "upload failed";
        }


    }

}
