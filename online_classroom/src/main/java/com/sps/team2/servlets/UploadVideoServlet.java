package com.sps.team2.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import java.nio.file.*;
import java.io.InputStream;
import java.io.File;
import org.apache.commons.io.*;

import com.sps.team2.classes.*;

@MultipartConfig
@WebServlet("/upload-video")
public class UploadVideoServlet extends HttpServlet {
  private final Video mVideo = new Video();
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Part filePart = request.getPart("videoFile");
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
    InputStream fileContent = filePart.getInputStream();
    String videoFolder = "src/main/webapp/videos/";
    String targetPath = videoFolder + fileName;
    String m3u8Folder = targetPath + "-list/";
    new File(m3u8Folder).mkdirs(); 

    File targetFile = new File(targetPath);
    java.nio.file.Files.copy(
      fileContent, 
      targetFile.toPath(), 
      StandardCopyOption.REPLACE_EXISTING);
    IOUtils.closeQuietly(fileContent);
    mVideo.transform(targetPath, m3u8Folder);
    response.sendRedirect("/dashboard.html");
  }
}