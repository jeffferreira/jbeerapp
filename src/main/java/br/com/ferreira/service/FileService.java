package br.com.ferreira.service;

import br.com.ferreira.domain.Recipe;
import br.com.ferreira.xstream.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class FileService {

    public Recipe store(MultipartFile multipart) {
        BufferedReader br;

        try {
            String[] validExtentions = {"xml"};
            String originalFileName = multipart.getOriginalFilename();
            int dot = originalFileName.lastIndexOf('.');
            String extension = (dot == -1) ? "" : originalFileName.substring(dot + 1);
            if (!Arrays.asList(validExtentions).contains(extension.toLowerCase())) {
                throw new RuntimeException("Extension FAIL!");
            }

            String line;
            StringBuilder xmlStr = new StringBuilder();
            InputStream is = multipart.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                xmlStr.append(line);
            }

            Object recipesDTO =  convertXmlToDto(xmlStr.toString());

            return null;


        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    private static Object convertXmlToDto(String xmlStr) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(EquipamentDTO.class);
        xstream.processAnnotations(FermentableDTO.class);
        xstream.processAnnotations(HopDTO.class);
        xstream.processAnnotations(MashDTO.class);
        xstream.processAnnotations(MashStepDTO.class);
        xstream.processAnnotations(RecipeDTO.class);
        xstream.processAnnotations(RecipesDTO.class);
        xstream.processAnnotations(StyleDTO.class);
        xstream.processAnnotations(YeastDTO.class);

        return xstream.fromXML(xmlStr);
    }

}
