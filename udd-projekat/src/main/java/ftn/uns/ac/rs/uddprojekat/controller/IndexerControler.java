package ftn.uns.ac.rs.uddprojekat.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.uns.ac.rs.uddprojekat.handler.PDFHandler;
import ftn.uns.ac.rs.uddprojekat.indexer.Indexer;
import ftn.uns.ac.rs.uddprojekat.model.Autor;
import ftn.uns.ac.rs.uddprojekat.model.IndexUnit;
import ftn.uns.ac.rs.uddprojekat.model.dto.FileUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IndexerControler {


    @Autowired
    Indexer indexer;
    @Value("${dataDir}")
    private String DATA_DIR_PATH;

    @PostMapping(value = "/index/add")
    public ResponseEntity<?> addFile(@ModelAttribute FileUploadDto file) {
        System.out.println("Dodajem fajl");
//        System.out.println(file.toString());

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Autor> autors = mapper.readValue(file.getJsonAutors(), new TypeReference<List<Autor>>() {
            });
            file.setAutors(autors);
            System.out.println(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            indexUploadedFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            System.out.println(file.getOriginalFilename());
            File path = new File(DATA_DIR_PATH + file.getOriginalFilename());
            Files.write(Paths.get(path.getAbsolutePath()), bytes);
            retVal = path.toString();
        }
        return retVal;
    }


    private void indexUploadedFile(FileUploadDto model) throws IOException {
        MultipartFile file = model.getFile();
        if (!file.isEmpty()) {
            String fileName = saveUploadedFile(file);
            if (fileName != null) {
                System.out.println(fileName);
                IndexUnit indexUnit = new PDFHandler().getIndexUnit(new File(fileName));
                indexUnit.setTitle(model.getTitle());
                indexUnit.setKeywords(model.getKeywords());
                indexUnit.setMagazine(model.getMagazine());
                indexUnit.setCategory(model.getCategory());
                indexUnit.setDocument_abstract(model.getApstract());
                indexUnit.setPath(fileName);
                indexUnit.setAutors(model.getAutors());
                System.out.println(indexUnit.toString());
                indexer.add(indexUnit);
            }
        }


    }

    @GetMapping(value = "/get")
    public ResponseEntity<Resource> getFile(@RequestParam String path) throws Exception {
        try {
//            Path pathToDir = Paths.get(DATA_DIR_PATH);
//            Path file = pathToDir.resolve(path);
//            System.out.println(file.toString());
            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                String contentType="application/pdf";
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
            else {
                throw new Exception("FILE NOT FOUND");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
