package com.sps.team2.classes;


import net.bramp.ffmpeg.*;
import net.bramp.ffmpeg.builder.*;
import java.io.IOException;


/**
 * Provides operations for videos.
 */
public class Video {


    public void transform(String inputFile, String outputFolder) {
        String outputM3u8 = outputFolder + "playlist.m3u8";
        String outputTs = outputFolder + "out%03d.ts";
        String cmd = "ffmpeg -i " + inputFile + " -codec:v libx264 -codec:a mp3 -map 0 -f ssegment -segment_format mpegts -segment_list " + outputM3u8 + " -segment_time 10 " + outputTs;
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(cmd);
            p.waitFor();
        } catch (Exception e) {

        }
        /*
        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;
        try {
            ffmpeg = new FFmpeg("/usr/bin/ffmpeg");
            ffprobe = new FFprobe("/usr/bin/ffprobe");
        }
        catch(IOException e) {
          e.printStackTrace();
        }
        FFmpegBuilder builder = new FFmpegBuilder()
        .setInput(input)     // Filename, or a FFmpegProbeResult
        .overrideOutputFiles(true) // Override the output if it exists
        .addOutput("output.m3u8")   // Filename for the destination
            .setFormat("m3u8")        // Format is inferred from filename, or can be set
            .setVideoCodec("libx264")     // Video using x264
            .setVideoFrameRate(24, 1)     // at 24 frames per second
            .setVideoResolution(640, 480) // at 640x480 resolution
            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
            .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
        */
    }
}