package com.dynamic.register.controller;

import com.dynamic.register.common.utils.FileUtil;
import com.dynamic.register.entity.ImageModel;
import com.dynamic.register.gcp.OcrVisionService;
import com.dynamic.register.repository.FileRepo;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


//@Controller
//@RequestMapping("/fileUpload")
//@CrossOrigin(origins = "http://localhost:4200")
//public class ImageUploadController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadController.class);
//
//    @PostMapping("/ocr/converter")
//    public void analyzeFile(@RequestParam("file") MultipartFile file) throws IOException {
//        BytePointer outText;
//        TessBaseAPI api = new TessBaseAPI();
//        if (api.Init(null, "eng") != 0){
//            LOGGER.error("tesseract has not initialized properly!");
//            throw new IOException();
//        }
//        PIX image = pixRead(file.getName());
//        api.SetImage(image);
//        String text = api.GetUTF8Text().getString();
//        LOGGER.info((text != null) ? text : "ERRRRRRRRRORRRRR");
//    }
//
////    public static File convert(MultipartFile file) throws IOException {
////        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
////        convertedFile.createNewFile();
////        FileOutputStream fos = new FileOutputStream(convertedFile);
////        fos.write(file.getBytes());
////        fos.close();
////        return convertedFile;
////    }
//}



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class ImageUploadController {

        private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadController.class);

    final FileRepo imageRepository;
    final OcrVisionService ocrVisionService;

    public ImageUploadController(FileRepo imageRepository, OcrVisionService ocrVisionService) {
        this.imageRepository = imageRepository;
        this.ocrVisionService = ocrVisionService;
    }

    @PostMapping("/upload")
    public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException, TesseractException {
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("src/main/resources/tessdata");
//        tesseract.setLanguage("eng");
//        tesseract.setPageSegMode(1);
//        tesseract.setOcrEngineMode(1);
//        String result = tesseract.doOCR(FileUtil.convert(file));
        ocrVisionService.ocrLabeling(file);
        String result = ocrVisionService.extractText(file);
        LOGGER.info(result + "###########################");


//        Ocr.setUp();
//        Ocr ocr = new Ocr();
//        ocr.startEngine("eng", Ocr.SPEED_FAST);
////        File[] files = new File[]{FileUtil.convert(file)};
//        String s = ocr.recognize(new File[]{FileUtil.convert(file)}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_XML);




//        BytePointer outText;
//        TessBaseAPI api = new TessBaseAPI();
//        api.Init(null, "eng");
////        if (api.Init(null, "eng") != 0){
////            LOGGER.error("tesseract has not initialized properly!");
////            throw new IOException();
////        }
//        PIX image = pixRead(file.getName());
//        api.SetImage(image);
//        outText = api.GetUTF8Text();
//        String text = outText.getString();
//        LOGGER.info((text != null) ? text : "ERRRRRRRRRORRRRR");


        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                FileUtil.compressBytes(file.getBytes()));
        imageRepository.save(img);

//        api.End();
//        outText.deallocate();
//        pixDestroy(image);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = {"/get/{imageName}"})
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                FileUtil.decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }
}









