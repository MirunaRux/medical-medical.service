package medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import medical.dao.FileDao;
import medical.model.File;

@Service
public class FileService {
    private static List<File> files;

    @Autowired
    private FileDao fileDao;

    public List<File> findAllFiles() {
        files = fileDao.getAllFiles();
        return files;
    }

    public File createFile(File File) {
        fileDao.create(File);
        return File;
    }

    public void updateFile(File File) {
        fileDao.update(File);
    }

    public File findById(String id) {
        files = fileDao.getAllFiles();
        for (File file : files) {
            if (file.getId().equals(id)) {
                return file;
            }
        }
        return null;
    }
}
