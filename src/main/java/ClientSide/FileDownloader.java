package ClientSide;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class FileDownloader implements Runnable{
    List<String> data;

    public FileDownloader(List<String> data) {
        this.data = data;
    }

    @Override
    public void run() {
        File fileToDownload = new File(ClientConfig.downloadedFileUrl+data.get(1));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
            for (int i = 2;i<data.size();i++){
                fileOutputStream.write(Integer.parseInt(data.get(i)));
            }
            fileOutputStream.close();
            JOptionPane.showMessageDialog(null,"FILE DOWNLOADED SUCCESSFULLY!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
