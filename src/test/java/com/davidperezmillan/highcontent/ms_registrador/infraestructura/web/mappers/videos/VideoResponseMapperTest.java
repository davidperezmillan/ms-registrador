package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.videos;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.videos.VideoResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class VideoResponseMapperTest {


    private static final String SIZE_1KB = "1.0 KB";

    @Test
    void map() {
        assertDoesNotThrow(
                () -> {
                    VideoFile videoFile = createVideoFile();
                    VideoResponse videoResponse = VideoResponseMapper.map(videoFile);
                    assertEquals(videoFile.getFileName(), videoResponse.getFileName());
                    assertEquals(videoFile.getPath(), videoResponse.getPath());
                    assertEquals(videoFile.getExtension(), videoResponse.getExtension());
                    assertEquals(SIZE_1KB, videoResponse.getSize());
                }
        );
    }

    @Test
    void testMap() {
        assertDoesNotThrow(
                () -> {
                    List<VideoFile> videoFiles = createVideoFiles();
                    List<VideoResponse> videoResponses = VideoResponseMapper.map(videoFiles);
                    assertEquals(videoFiles.size(), videoResponses.size());
                    VideoFile videoFile = videoFiles.get(0);
                    VideoResponse videoResponse = videoResponses.get(0);
                    assertEquals(videoFile.getFileName(), videoResponse.getFileName());
                    assertEquals(videoFile.getPath(), videoResponse.getPath());
                    assertEquals(videoFile.getExtension(), videoResponse.getExtension());
                    assertEquals(SIZE_1KB, videoResponse.getSize());

                }
        );

    }

    @Test
    void formatSize() {
        assertDoesNotThrow(
                () -> {
                    VideoFile videoFile = createVideoFile();
                    String size = VideoResponseMapper.formatSize(videoFile.getSize());
                    assertEquals(SIZE_1KB, size);
                }
        );
    }

    private List<VideoFile> createVideoFiles() {
        return List.of(createVideoFile());
    }

    private VideoFile createVideoFile() {
        VideoFile videoFile = new VideoFile();
        videoFile.setFileName("video.mp4");
        videoFile.setPath("/home/user/videos");
        videoFile.setExtension("mp4");
        videoFile.setSize(1024); // SIZE_1KB

        return videoFile;
    }

}