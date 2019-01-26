package whoisacat;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BZip2Dearchator{


    public void start(String fileName) throws IOException{

        String tempDirPath = checkTempDir();
        File archive = new File(tempDirPath + "/" + fileName);
        if(archive.exists()){
            InputStream fin = null;
            try{
                fin = Files.newInputStream(Paths.get(tempDirPath + "/" + archive.getName()));
                BufferedInputStream in = new BufferedInputStream(fin);

                BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);

                final byte[] buffer = new byte[20 * 1024];

                OutputStream out = Files.newOutputStream(
                        Paths.get(tempDirPath + "/" + getCurrentTimeStamp() + fileName + ".xml"));
                int n = 0;
                while(-1 != (n = bzIn.read(buffer))){
                    out.write(buffer,0,n);
                }
                out.close();
                bzIn.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        } else throw new IOException("There is not any archive with name " + fileName);
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss:SSS_");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private String checkTempDir() throws IOException{
        File tempDir = new File("./temp_dir");
        if(!tempDir.exists() | !tempDir.isDirectory()){
            tempDir.mkdir();
            throw new IOException("There was no tem directory");
        }
        return tempDir.getAbsolutePath();
    }

}
