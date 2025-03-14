package com.scm.contactmanager.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    
    String uploadImage(MultipartFile multipartFile,String filename);

    String getURlFromPublicId(String publicid);
}
