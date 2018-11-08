package com.example.demopojocrud.services.implemetation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
//the @PropertySource annotation use for call the property file
@PropertySource("classpath:/project_file.properties")
public class FileUploadService {
    //set value (path) for variable SERVER_PATH;
    @Value("${server.path}")
    private String SERVER_PATH;

    //for single file upload
    private String singleFileUpload(MultipartFile file, String folder){
        //create directory if not exist
        File path = new File(SERVER_PATH + folder);
        if(folder == null)
            folder ="";
        if(!path.exists())
            path.mkdirs();

        //create new random file name
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".png"));
        fileName = UUID.randomUUID() + "." +extension;
        System.out.println(extension);
        System.out.println(fileName);

        //copy file from pc to server
        try {
            Files.copy(file.getInputStream(), Paths.get(SERVER_PATH + folder, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder+fileName;
    }
    //for multiple files upload
    private List<String> multipleFileUpload(List<MultipartFile> files, String folder){
        List<String> fileNames = new ArrayList<>();

//        //for i loop, not recommanded to use
//        for (int i = 0; i <files.size() ; i++) {
//            String fname = this.singleFileUpload(files.get(i), folder);
//            fileNames.add(fname);
//        }
//
//        //foreach loop
//        for (MultipartFile file: files
//             ) {
//            String fname = this.singleFileUpload(file, folder);
//            fileNames.add(fname);
//        }

        //for loop using lamda expression
        files.forEach(file -> {
            fileNames.add(this.singleFileUpload(file, folder));
        });

        return fileNames;
    }

    //in the case of using more method, better use overloading as below
    //overloading will create the same method name, but just different parameters
    //********
    //this is the method for upload single file with 2 parameters (file and folder)
    public String upload(MultipartFile file, String folder){
        return this.singleFileUpload(file, folder);
    }

    //this is the method for upload single file with 1 para (file with null)
    public String upload(MultipartFile file){
        return this.singleFileUpload(file, null);
    }
    //this is method for upload multiple file with 2 para (files and folder)
    public List<String> upload(List<MultipartFile> files, String folder){
        return this.multipleFileUpload(files, folder);
    }

    public List<String> upload (List<MultipartFile> files){
        return this.multipleFileUpload(files, null);
    }



}
