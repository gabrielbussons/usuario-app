package com.app.usuario.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String salvar(MultipartFile file, String nome) throws IOException {
       
		byte[] bytes = file.getBytes();

        String rootPath = System.getProperty("user.home");
        File dir = new File(rootPath + File.separator + "userFiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + nome);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        
        return serverFile.getAbsolutePath();

    }
}
