package com.dynamic.register.controller;

import com.dynamic.register.repository.FileRepo;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class ImageUploadController {

        private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadController.class);

    final FileRepo imageRepository;

    public ImageUploadController(FileRepo imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public BodyBuilder uploadImage(@RequestBody String imageData) throws IOException, TesseractException {
        String result = null;
        try {
            FileUtils.writeByteArrayToFile(new File("/home/giannis/IdeaProjects/DynamicRegister/src/main/resources/data.png"), convertToByteArray(imageData));
            File image = new File("/home/giannis/IdeaProjects/DynamicRegister/src/main/resources/data.png");
            Tesseract tesseract = new Tesseract();
            tesseract.setOcrEngineMode(1);
            tesseract.setLanguage("ell");
            tesseract.setDatapath("/usr/local/share/tessdata");
            result = tesseract.doOCR(image);
            image.delete();
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        LOGGER.info(result);

        return ResponseEntity.status(HttpStatus.OK);
    }

    public static byte[] convertToByteArray(String data) {
        String encodingPrefix = "base64,";
        int contentStartIndex = data.indexOf(encodingPrefix) + encodingPrefix.length();
        return Base64.getDecoder().decode((data.substring(contentStartIndex)));
    }
}









