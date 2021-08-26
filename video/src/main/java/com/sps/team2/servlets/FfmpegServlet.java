package com.sps.team2.servlets;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ffmpeg-encode")
public class FfmpegServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    BasicConfigurator.configure();
    FFmpeg ffmpeg = new FFmpeg("/usr/bin/ffmpeg");
    FFprobe ffprobe = new FFprobe("/usr/bin/ffprobe");

    FFmpegBuilder builder = new FFmpegBuilder()

      .setInput("./src/main/webapp/WEB-INF/clip.mp4")
      .overrideOutputFiles(true)

      .addOutput("./src/main/webapp/WEB-INF/output.mp4")
      .setAudioCodec("aac")        // using the aac codec
      .setAudioSampleRate(48_000)  // at 48KHz
      .setAudioBitRate(32768)      // at 32 kbit/s
      .setVideoCodec("libx264")     // Video using x264
      .setVideoFrameRate(24, 1)     // at 24 frames per second
      .setVideoResolution(640, 480) // at 640x480 resolution
      .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
      .done();

      FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

      // Run a one-pass encode
      executor.createJob(builder).run();
      response.sendRedirect("/");
  }
}