package com.scm.contactmanager.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.contactmanager.entities.AppConstants;
import com.scm.contactmanager.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ImageServiceImpl.class); 
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary){
        this.cloudinary= cloudinary;
    }
    @Override
    public String uploadImage(MultipartFile contactImage ,String filename) {
        
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id",filename));
            return getURlFromPublicId(filename);
        } catch (IOException e) {
           
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public String getURlFromPublicId(String publicid) {
       return cloudinary.url().transformation(new Transformation<>().width(AppConstants.WIDTH).height(AppConstants.LENGTH).crop("fill")).generate(publicid);
    }
    
}
