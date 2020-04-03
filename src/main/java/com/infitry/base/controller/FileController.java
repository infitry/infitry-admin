package com.infitry.base.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * @since 2020. 4. 03.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 파일  컨트롤러
 */
@Controller
@RequestMapping("/file")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	RestTemplate fileClient = new RestTemplate();
	
	@Value("${infitry.blog.url}")
	String blogUrl;
	
	@Value("${infitry.file.upload}")
	String uploadPath;
	
	/**
	 * @since 2020. 4. 03.
	 * @author leesw
	 * @description : 파일 업로드 컨트롤러
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void fileUpload(HttpServletResponse response, 
			@RequestParam MultipartFile upload, String CKEditorFuncNum) {
		// 한글깨짐을 방지하기위해 문자셋 설정
        response.setCharacterEncoding("utf-8");
        // 마찬가지로 파라미터로 전달되는 response 객체의 한글 설정
        response.setContentType("text/html; charset=utf-8");
 
        // 업로드한 파일 이름
        String fileName = upload.getOriginalFilename();
        PrintWriter printWriter = null;
        OutputStream out = null;
        String callback = "";
        String fileUrl = "";
        try {
	        byte[] bytes = upload.getBytes();
	        out = new FileOutputStream(new File(uploadPath + fileName));
	        logger.info("=========================== imager upload start ===========================");
	        out.write(bytes);
	        logger.info("=========================== imager upload end =============================");
	        
	        callback = CKEditorFuncNum;
	        logger.info("callbackFn name : " + callback);
	        printWriter = response.getWriter();
	        fileUrl = uploadPath + fileName;
	        logger.info("fileUrl : " + fileUrl);
	        printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl
	                + "','이미지가 업로드되었습니다.')" + "</script>");
        } catch (Exception e) {
        	logger.error("이미지 업로드 실패 : " + e.getMessage());
	        printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl
	                + "','이미지 업로드에 실패하였습니다.')" + "</script>");
        } finally {
        	try {
				out.flush();
				out.close();
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
