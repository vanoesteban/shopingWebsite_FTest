package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class File_IO {


    public static Properties loadProps(String File) throws Exception{
        Properties props = new Properties();
        props.load(new FileInputStream(File));
        return props;
    }
}
