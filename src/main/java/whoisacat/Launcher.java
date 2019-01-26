package whoisacat;

import java.io.IOException;

public class Launcher{

    public static void main(String[] args){
        try{
            BZip2Dearchator dearchator = new BZip2Dearchator();
            dearchator.start("state_list.xml.bzip");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
