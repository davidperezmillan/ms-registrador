package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoResponseMapperTest {

    @Test
    void map() {
        assertDoesNotThrow(
                () -> {
                    VideoFile videoFile = createVideoFile();
                    VideoResponse videoResponse = VideoResponseMapper.map(videoFile);
                    assertEquals(videoFile.getFileName(), videoResponse.getFileName());
                    assertEquals(videoFile.getPath(), videoResponse.getPath());
                    assertEquals(videoFile.getExtension(), videoResponse.getExtension());
                    assertEquals("1,0 KB", videoResponse.getSize());
                    assertEquals(videoFile.getCreationDate(), videoResponse.getCreationDate());
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
                    assertEquals("1,0 KB", videoResponse.getSize());
                    assertEquals(videoFile.getCreationDate(), videoResponse.getCreationDate());

                }
        );

    }

    @Test
    void formatSize() {
        assertDoesNotThrow(
                () -> {
                    VideoFile videoFile = createVideoFile();
                    String size = VideoResponseMapper.formatSize(videoFile.getSize());
                    assertEquals("1,0 KB", size);
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
        videoFile.setSize(1024);
        videoFile.setCreationDate("2021-09-01");
        return videoFile;
    }
}