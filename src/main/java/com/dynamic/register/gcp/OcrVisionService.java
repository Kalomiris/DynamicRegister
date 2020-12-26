package com.dynamic.register.gcp;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OcrVisionService {

    private final CloudVisionTemplate cloudVisionTemplate;
    @Autowired
    private ResourceLoader resourceLoader;

    public OcrVisionService(CloudVisionTemplate cloudVisionTemplate) {
        this.cloudVisionTemplate = cloudVisionTemplate;
    }

    public void ocrLabeling(MultipartFile file) {
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(file.getResource(), Feature.Type.LABEL_DETECTION);

        Map<String, Float> imageLabels =
                response
                        .getLabelAnnotationsList()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        EntityAnnotation::getDescription,
                                        EntityAnnotation::getScore,
                                        (u, v) -> {
                                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                                        },
                                        LinkedHashMap::new));
    }

    public String extractText (MultipartFile file) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(file.getResource());
        return "Text from image: " + textFromImage;
    }
}
