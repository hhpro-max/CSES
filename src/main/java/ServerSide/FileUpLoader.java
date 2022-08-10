package ServerSide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUpLoader implements Runnable{
    List<String> order;
    public FileUpLoader(List<String> order){
        this.order = order;
    }
    @Override
    public void run() {
        File uploadedFile = new File(Config.uploadedFilesUrl+order.get(2));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
            for (int i = 3;i<order.size();i++){
                fileOutputStream.write(Integer.parseInt(order.get(i)));
            }
            fileOutputStream.close();
            System.out.println("SUCCESSFULLY UPLOADED!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
