package medical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import medical.model.File;
import medical.service.FileService;
import medical.util.MedicalServiceErrorType;

@RestController
@RequestMapping("/medicalService/api")
public class FileRestController {
    public static final Logger logger = LoggerFactory.getLogger(FileRestController.class);

    @Autowired
    FileService fileService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ResponseEntity<List<File>> listAllFiles() {
        List<File> files = fileService.findAllFiles();
        if (files.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<File>>(files, HttpStatus.OK);
    }

    // -------------------Create a File-------------------------------------------

    @RequestMapping(value = "/file/", method = RequestMethod.POST)
    public ResponseEntity<?> createFile(@RequestBody File file) {
        logger.info("Creating File : {}", file);

        File newFile = fileService.createFile(file);

        return new ResponseEntity<File>(newFile, HttpStatus.OK);
    }


    // ------------------- Update a File ------------------------------------------------

    @RequestMapping(value = "/file/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateFile(@PathVariable("id") String id, @RequestBody File file) {
        logger.info("Updating File with id {}", id);

        File currentFile = fileService.findById(id);

        fileService.updateFile(currentFile);
        return new ResponseEntity<File>(currentFile, HttpStatus.OK);
    }

}
