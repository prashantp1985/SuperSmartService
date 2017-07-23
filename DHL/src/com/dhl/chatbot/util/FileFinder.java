/**
 * This class indicates PathFinder.java
 */
package com.dhl.chatbot.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class FileFinder {

	private final String CONFIG = "/DHL/images/";
	private final String IMAGE_FILE_PATH = "/images/ganapathy.jpg";
	private final String CALENDER_IMAGE_FILE_PATH = "/images/calenderImage.jpg";
	private final String JASPER_FILE_PATH = "/reports/";
	private final String ENCRYPTION_FILE_PATH = "com/dhl/chatbot/authentication/encryptionKey.properties";
	private final String HIBERNATE_FILE_PATH = "C:/Prashant/workspace/DHL/config/";
	private final String DISCLAIMER_FILE_PATH = "sri/ram/mandir/disclaimer/Disclaimer.txt";
	private final String REPORT_GENERATOR_FILE_PATH = "/reports/reportGenerator.properties";
	private final String PERSISTOR_FILE_PATH = "/persistence/persistence.properties";
	private final String DCC_IMAGE_FILE_PATH = "C:/Prashant/workspace/DHL/images/dhlLogo.jpg";
	
	public String getDCCImagePath() {
		return DCC_IMAGE_FILE_PATH;
	}
	
	public ImageIcon getCalenderImageIcon() {
		ImageIcon imageIcon = null;		
//		InputStream iStr = FileFinder.class.getClassLoader().getResourceAsStream(this.CONFIG + CALENDER_IMAGE_FILE_PATH);
		URL url = FileFinder.class.getClassLoader().getResource(this.CONFIG + CALENDER_IMAGE_FILE_PATH);
		if (url != null) {
			imageIcon = new ImageIcon(url);
		}
		return imageIcon;
	}
	
	public InputStream getImageInputStream() {
		return getInputStream(IMAGE_FILE_PATH);
	}
	
	public InputStream getEncryptionInputStream() {
		return getInputStream(ENCRYPTION_FILE_PATH);
	}
	
	public InputStream getReportGeneratorInputStream() {
		return getInputStream(REPORT_GENERATOR_FILE_PATH);
	}
	
	public InputStream getPersistorSessionInputStream() {
		return getInputStream(PERSISTOR_FILE_PATH);
	}
	
	public InputStream getReportInputStream(String inputFileName) {
		return getInputStream(JASPER_FILE_PATH + inputFileName + ".jrxml");
	}
	
	public InputStream getDisclaimerInputStream() {
		InputStream iStr = FileFinder.class.getClassLoader().getResourceAsStream(DISCLAIMER_FILE_PATH);
		return iStr;
	}
	
	public File getHibernateFile() {
//		InputStream iStr = getInputStream(HIBERNATE_FILE_PATH);
		File f = new File(HIBERNATE_FILE_PATH + "hibernate.cfg.xml");
//		 try
//		    {
//		    
//		    InputStream inputStream= new FileInputStream(iStr);
//		    OutputStream out=new FileOutputStream(f);
//		    byte buf[]=new byte[1024];
//		    int len;
//		    while((len=inputStream.read(buf))>0)
//		    out.write(buf,0,len);
//		    out.close();
//		    inputStream.close();
//		    }
//		    catch (IOException e){}
		    
		return f;
	}
	
	public InputStream getInputStream(String inputFileName) {
//		String path = System.getProperty("user.dir");
		InputStream iStr = FileFinder.class.getClassLoader().getResourceAsStream(inputFileName);
//		File input = new File(path + "/src/" + CONFIG + inputFileName);
//        if (! input.exists()) {
//        	input = new File(path + "/" + CONFIG + inputFileName);
//        } else {
//        	path = path + "/src/" + CONFIG + inputFileName;
//        	return path;
//        }
//        if (! input.exists()) {
//        	input = new File(path + inputFileName);
//        } else {
//        	path = path + "/" + CONFIG + inputFileName;
//        	return path;
//        }
//        return "/" + CONFIG + inputFileName;
      return iStr;
	}
	
	public URL getResourceURL() {
//		String path = System.getProperty("user.dir");
		URL url = FileFinder.class.getClassLoader().getResource(this.CONFIG + HIBERNATE_FILE_PATH);
//		File folder = new File(url.get);
//		  File[] listOfFiles = folder.listFiles();
//		 
//		  for (int i = 0; i < listOfFiles.length; i++)
//		  {
//		 
//		   if (listOfFiles[i].isFile())
//		   {
//		   files = listOfFiles[i].getName();
//		   System.out.println(files);
//		      }
//		  }
//		File input = new File(path + "/src/" + CONFIG + inputFileName);
//        if (! input.exists()) {
//        	input = new File(path + "/" + CONFIG + inputFileName);
//        } else {
//        	path = path + "/src/" + CONFIG + inputFileName;
//        	return path;
//        }
//        if (! input.exists()) {
//        	input = new File(path + inputFileName);
//        } else {
//        	path = path + "/" + CONFIG + inputFileName;
//        	return path;
//        }
//        return "/" + CONFIG + inputFileName;
      return url;
	}
	
	public String getStringPath(String inputFileName) {
		 
//       return url.getPath();
//		java.util.Properties props = new java.util.Properties();
//		String path = getClass().getProtectionDomain().getCodeSource().
//		   getLocation().toString().substring(6);
//		java.io.FileInputStream fis = new java.io.FileInputStream
//		   (new java.io.File( path + "/myprops.props"));
//		props.load(fis);
//		fis.close();
//		System.out.println(props);
		
		
		String path = CONFIG + inputFileName; 
    	File input = new File(path);
        if (! input.exists()) {
        	path = "src/" + CONFIG + inputFileName;
        	input = new File(path);
        } else {
        	path = CONFIG + inputFileName;
        	
        }
		return path;
//		
//		String path = System.getProperty("user.dir");
//		File input = new File(path + "/src/" + CONFIG + inputFileName);
//        if (! input.exists()) {
//        	input = new File(path + "/" + CONFIG + inputFileName);
//        } else {
//        	path = path + "/src/" + CONFIG + inputFileName;
//        	return path;
//        }
//        if (! input.exists()) {
//        	input = new File(path + inputFileName);
//        } else {
//        	path = path + "/" + CONFIG + inputFileName;
//        	return path;
//        }
//        URL url = PathFinder.class.getClassLoader().getResource(this.CONFIG + inputFileName);
//        return url.getPath();
//        return "/" + CONFIG + inputFileName;
	}
	
	
	public static void main(String[] args) throws IOException {
		FileFinder f = new FileFinder();
		InputStream iStr = f.getImageInputStream();
		Properties props = new java.util.Properties();
		props.load(iStr);
		System.out.println(props);
		
		iStr = f.getReportInputStream("all items report");
		props = new java.util.Properties();
		props.load(iStr);
		System.out.println(props);
	}

	/**
	 * This method is used to getHibernateResourceURL
	 * @return
	 * @return URL[]
	 */
	public List<URL> getHibernateResourceURL() {
		List<URL> urls = new ArrayList<URL>(); 
		try {
			java.util.Properties props = new java.util.Properties();
			java.io.InputStream fis = getInputStream(HIBERNATE_FILE_PATH + "hibernateMappingList.properties");
			props.load(fis);
			fis.close();
			for ( Entry<Object, Object> e : props.entrySet() ) {
//	          String key = e.getKey().toString();
	          String value = e.getValue().toString();
	          URL url = FileFinder.class.getClassLoader().getResource(this.CONFIG + HIBERNATE_FILE_PATH + value);
	          urls.add(url);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return urls;
	}
	
//  private void loadHibernateCfgXml(Properties config,
//  HibernateUtilConfig hibernateConfig) {
//// locates hibernate.cfg.xml file
//String[] hibernateCfg =
//      config.getProperty("hibernate.cfg.xml").split(",", 2);
//if ( hibernateCfg[0].equalsIgnoreCase(RES_LOC_RESOURCE) ) {
//  hibernateConfig.setPropertyFile(loadResourceAsUrl(hibernateCfg[1]));
//  LOGGER.info("Load hibernate.cfg.xml from resource "
//          + hibernateCfg[1]);
//} else if ( hibernateCfg[0].equalsIgnoreCase(RES_LOC_CLASSLOADER) ) {
//  hibernateConfig.setPropertyFile(getClassLoader().getResource(
//          hibernateCfg[1]));
//  LOGGER.info("Load hibernate.cfg.xml from " + hibernateCfg[1]
//          + " using custom class loader");
//} else {
//  hibernateConfig.setPropertyFile(new File(hibernateCfg[1]));
//  LOGGER.info("Load hibernate.cfg.xml from file " + hibernateCfg[1]);
//}
//}
//
///*
//* Loads properties.
//*/
//private void loadProperties(Properties config,
//  HibernateUtilConfig hibernateConfig) {
//for ( Entry<Object, Object> e : config.entrySet() ) {
//  String key = e.getKey().toString();
//  String value = e.getValue().toString();
//  if ( key.startsWith("property.") ) {
//      String propertyName = key.substring("property.".length());
//      String[] propertyValue = value.split(",", 2);
//      if ( propertyValue.length == 1 ) {
//          hibernateConfig.setProperty(propertyName, propertyValue[0]);
//          LOGGER.info("Set hibernate property: " + propertyName
//                  + " -> " + propertyValue[0]);
//      } else if ( propertyValue[0].equalsIgnoreCase(RES_LOC_CONTEXT) ) {
//          String v =
//                  getApplicationContext().getProperty(
//                          propertyValue[1]);
//          if ( v != null && v.trim().length() > 0 ) {
//              hibernateConfig.setProperty(propertyName, v);
//              LOGGER.info("Set hibernate property: " + propertyName
//                      + " -> " + v);
//          }
//      } else {
//          hibernateConfig.setProperty(propertyName, propertyValue[1]);
//          LOGGER.info("Set hibernate property: " + propertyName
//                  + " -> " + propertyValue[1]);
//      }
//  }
//}
//}
//
/*
* Loads mapping configurations.
*/
//private void loadMappings(Properties config
//  ) {
//for ( Entry<Object, Object> e : config.entrySet() ) {
//  String key = e.getKey().toString();
//  String value = e.getValue().toString();
//  if ( key.startsWith("mapping.") || key.endsWith(".hbm.xml") ) {
//      String[] resourceFile = value.split(",", 2);
//      if ( resourceFile[0].equalsIgnoreCase(RES_LOC_RESOURCE) ) {
//          hibernateConfig.addURL(loadResourceAsUrl(resourceFile[1]));
//      } else if ( resourceFile[0].equalsIgnoreCase(RES_LOC_CLASSLOADER) ) {
//          hibernateConfig.addURL(getClassLoader().getResource(
//                  resourceFile[1]));
//      } else {
//          hibernateConfig.addFile(new File(resourceFile[1]));
//          LOGGER.info("Load hibernate mapping from file "
//                  + resourceFile[1]);
//      }
//  }
//}


}
	
//public void init() throws Exception {
//// prepares configurations
//Properties config = getConfigurations();
//if ( config == null ) {
//  config = loadConfiguration(getHibernateConfigFile());
//}
//
//HibernateUtilConfig hibernateConfig = new HibernateUtilConfig();
//
//loadHibernateCfgXml(config, hibernateConfig);
//loadProperties(config, hibernateConfig);
//loadMappings(config, hibernateConfig);
//
//this.sessionFactory =
//      HibernateUtil.createSessionFactory(hibernateConfig);
//}

	
//}
