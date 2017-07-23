package com.dhl.chatbot.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class PropertyFileLoader {
	private static final String CONFIG = "sri/ram/mandir/config";
	
	private PropertyFileLoader() {  }
    /**
     * Load a properties file from the classpath
     * @param propsName
     * @return Properties
     * @throws Exception
     */
    public static Properties load(String propsName) throws Exception {
        Properties props = new Properties();
        URL url = ClassLoader.getSystemResource(propsName);
        props.load(url.openStream());
        return props;
    }

    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties load(File propsFile) throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(propsFile);
        props.load(fis);    
        fis.close();
        return props;
    }
    
    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties loadEncryptionPropertyByStream() throws IOException {
		java.util.Properties props = new java.util.Properties();
		java.io.InputStream fis = new FileFinder().getEncryptionInputStream();
		props.load(fis);
		fis.close();
    	return props;
    	
    }
    
    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties loadReportGeneratorPropertyByStream() throws IOException {
		java.util.Properties props = new java.util.Properties();
		java.io.InputStream fis = new FileFinder().getReportGeneratorInputStream();
		props.load(fis);
		fis.close();
    	return props;
    	
    }
    
    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties loadPersistorSessionInputStream() throws IOException {
		java.util.Properties props = new java.util.Properties();
		java.io.InputStream fis = new FileFinder().getPersistorSessionInputStream();
		props.load(fis);
		fis.close();
    	return props;
    	
    }
    
    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties loadByPath(String inputFileName) throws IOException {
//		java.util.Properties props = new java.util.Properties();
//		String path = PropertyFileLoader.class.getClass().getProtectionDomain().getCodeSource().
//		   getLocation().toString().substring(6);
//		System.out.println(path);
//		java.io.FileInputStream fis = new java.io.FileInputStream
//		   (new java.io.File( path + filename));
//		props.load(fis);
//		fis.close();
//		System.out.println(props);
//    	return props;
    	
    	String path = CONFIG + inputFileName; 
    	File input = new File(path);
        if (! input.exists()) {
        	path = "src/" + CONFIG + inputFileName;
        	input = new File(path);
        } else {
        	path = path + "/src/" + CONFIG + inputFileName;
        	
        }
//        if (! input.exists()) {
//        	input = new File(path + inputFileName);
//        } else {
//        	path = path + "/" + CONFIG + inputFileName;
//        }
    	
//    	File input = new File(inputFileName);
    	return load(input);
    }
}