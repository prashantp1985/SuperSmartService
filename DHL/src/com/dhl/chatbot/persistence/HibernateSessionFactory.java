package com.dhl.chatbot.persistence;
//package sri.ram.mandir.persistance;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URL;
//import java.sql.Connection;
//import java.util.Properties;
//import java.util.Map.Entry;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
/**
 * 
 * @author Prashant Padmanabhan
 *
 */
//public class HibernateSessionFactory {
//
//    private final static String RES_LOC_RESOURCE = "RESOURCE";
//
//    private final static String RES_LOC_CONTEXT = "CONTEXT";
//
//    private final static String RES_LOC_CLASSLOADER = "CLASSLOADER";
//
//    private final Log LOGGER = LogFactory.getLog(HibernateSessionFactory.class);
//
//    private String hibernateConfigFile;
//
//    private Properties configurations;
//
//    private DataSource dataSource;
//
//    private SessionFactory sessionFactory;
//
//    /**
//     * @since 1.0.5.1
//     */
//    private ClassLoader classLoader;
//
//    /**
//     * Gets custom ClassLoader instance which is used to load configuration and
//     * mapping files.
//     * 
//     * @return ClassLoader
//     * @since 1.0.5.1
//     */
//    public ClassLoader getClassLoader() {
//        return this.classLoader;
//    }
//
//    /**
//     * Sets custom ClassLoader instance which is used to load configuration and
//     * mapping files.
//     * 
//     * @param classLoader
//     * @since 1.0.5.1
//     */
//    public void setClassLoader(ClassLoader classLoader) {
//        this.classLoader = classLoader;
//    }
//
//    /**
//     * Gets configuration settings.
//     * 
//     * @return Properties
//     */
//    public Properties getConfigurations() {
//        return configurations;
//    }
//
//    /**
//     * Sets configuration settings.
//     * 
//     * @param configurations Properties
//     */
//    public void setConfigurations(Properties configurations) {
//        this.configurations = configurations;
//    }
//
//    /**
//     * Sets the DataSource to get Jdbc connections from.
//     * 
//     * @param dataSource DataSource
//     */
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//
//    /**
//     * Gets path to the configuration file.
//     * 
//     * @return String
//     */
//    public String getHibernateConfigFile() {
//        return hibernateConfigFile;
//    }
//
//    /**
//     * Sets path to the configuration file.
//     * 
//     * @param configFile String
//     */
//    public void setHibernateConfigFile(String configFile) {
//        this.hibernateConfigFile = configFile;
//    }
//
//
//    /**
//     * Gets a resource specified by a path and return an URL points to it.
//     * <p>
//     * This class use the method to load its internal data (e.g. Hibernate
//     * mapping files specified in HibernateUtilConfig's configuration file,
//     * Hibernate's <code>hibernate.cfg.xml</code> file, etc). Sub-class may
//     * override this method to provide its own resource loading mechanism.
//     * </p>
//     * <p>
//     * Current rule to load the resource: Use class' <code>getResource()</code>
//     * method.
//     * </p>
//     * 
//     * @param resourcePath String
//     * @return URL
//     */
//    protected URL loadResourceAsUrl(String resourcePath) {
//        return getClass().getResource(resourcePath);
//    }
//
//    /**
//     * Gets a resource specified by a path and return an InputStream to read
//     * data from it.
//     * <p>
//     * This class use the method to load master configuration settings for
//     * HibernateUtilsConfig. Sub-class may override this method to provide its
//     * own resource loading mechanism.
//     * </p>
//     * <p>
//     * Current rule to load the resource: Firstly, class'
//     * <code>getResourceAsStream()</code> is use. If <code>null</code> is
//     * returned, the method will try to load resource as a file whose path is
//     * specified by the resource path.
//     * </p>
//     * 
//     * @param resourcePath String
//     * @return InputStream
//     * @throws Exception
//     */
//    protected InputStream loadResource(String resourcePath) throws Exception {
//        InputStream is = getClass().getResourceAsStream(resourcePath);
//        if ( is != null ) {
//            return is;
//        }
//
//        File f = new File(resourcePath);
//        if ( f.isFile() && f.canRead() ) {
//            return new FileInputStream(f);
//        }
//        return null;
//    }
//
//    /**
//     * Loads configuration settings.
//     * 
//     * @param configPath String
//     * @return Properties
//     * @throws Exception
//     */
//    protected Properties loadConfiguration(String configPath) throws Exception {
//        Properties config = new Properties();
//        InputStream is = loadResource(configPath);
//        if ( is != null ) {
//            try {
//                config.load(is);
//                return config;
//            } finally {
//                try {
//                    is.close();
//                } catch ( Exception e ) {
//                }
//            }
//        }
//        return config;
//    }
//
//    /**
//     * Cleanup method
//     * 
//     * @throws Exception
//     */
//    public void destroy() throws Exception {
//    }
//
//    /*
//     * Locates & loads hibernate.cfg.xml file.
//     */
//    private void loadHibernateCfgXml(Properties config,
//            HibernateUtilConfig hibernateConfig) {
//        // locates hibernate.cfg.xml file
//        String[] hibernateCfg =
//                config.getProperty("hibernate.cfg.xml").split(",", 2);
//        if ( hibernateCfg[0].equalsIgnoreCase(RES_LOC_RESOURCE) ) {
//            hibernateConfig.setPropertyFile(loadResourceAsUrl(hibernateCfg[1]));
//            LOGGER.info("Load hibernate.cfg.xml from resource "
//                    + hibernateCfg[1]);
//        } else if ( hibernateCfg[0].equalsIgnoreCase(RES_LOC_CLASSLOADER) ) {
//            hibernateConfig.setPropertyFile(getClassLoader().getResource(
//                    hibernateCfg[1]));
//            LOGGER.info("Load hibernate.cfg.xml from " + hibernateCfg[1]
//                    + " using custom class loader");
//        } else {
//            hibernateConfig.setPropertyFile(new File(hibernateCfg[1]));
//            LOGGER.info("Load hibernate.cfg.xml from file " + hibernateCfg[1]);
//        }
//    }
//
//    /*
//     * Loads properties.
//     */
//    private void loadProperties(Properties config,
//            HibernateUtilConfig hibernateConfig) {
//        for ( Entry<Object, Object> e : config.entrySet() ) {
//            String key = e.getKey().toString();
//            String value = e.getValue().toString();
//            if ( key.startsWith("property.") ) {
//                String propertyName = key.substring("property.".length());
//                String[] propertyValue = value.split(",", 2);
//                if ( propertyValue.length == 1 ) {
//                    hibernateConfig.setProperty(propertyName, propertyValue[0]);
//                    LOGGER.info("Set hibernate property: " + propertyName
//                            + " -> " + propertyValue[0]);
//                } else if ( propertyValue[0].equalsIgnoreCase(RES_LOC_CONTEXT) ) {
//                    String v =
//                            getApplicationContext().getProperty(
//                                    propertyValue[1]);
//                    if ( v != null && v.trim().length() > 0 ) {
//                        hibernateConfig.setProperty(propertyName, v);
//                        LOGGER.info("Set hibernate property: " + propertyName
//                                + " -> " + v);
//                    }
//                } else {
//                    hibernateConfig.setProperty(propertyName, propertyValue[1]);
//                    LOGGER.info("Set hibernate property: " + propertyName
//                            + " -> " + propertyValue[1]);
//                }
//            }
//        }
//    }
//
//    /*
//     * Loads mapping configurations.
//     */
//    private void loadMappings(Properties config,
//            HibernateUtilConfig hibernateConfig) {
//        for ( Entry<Object, Object> e : config.entrySet() ) {
//            String key = e.getKey().toString();
//            String value = e.getValue().toString();
//            if ( key.startsWith("mapping.") || key.endsWith(".hbm.xml") ) {
//                String[] resourceFile = value.split(",", 2);
//                if ( resourceFile[0].equalsIgnoreCase(RES_LOC_RESOURCE) ) {
//                    hibernateConfig.addURL(loadResourceAsUrl(resourceFile[1]));
//                    LOGGER.info("Load hibernate mappings from resource "
//                            + resourceFile[1]);
//                } else if ( resourceFile[0].equalsIgnoreCase(RES_LOC_CLASSLOADER) ) {
//                    hibernateConfig.addURL(getClassLoader().getResource(
//                            resourceFile[1]));
//                    LOGGER.info("Load hibernate mappings from resource "
//                            + resourceFile[1] + " using custom class loader.");
//                } else {
//                    hibernateConfig.addFile(new File(resourceFile[1]));
//                    LOGGER.info("Load hibernate mapping from file "
//                            + resourceFile[1]);
//                }
//            }
//        }
//    }
//
//    /**
//     * Initializes the factory.
//     * 
//     * @throws Exception
//     */
//    public void init() throws Exception {
//        // prepares configurations
//        Properties config = getConfigurations();
//        if ( config == null ) {
//            config = loadConfiguration(getHibernateConfigFile());
//        }
//
//        HibernateUtilConfig hibernateConfig = new HibernateUtilConfig();
//
//        loadHibernateCfgXml(config, hibernateConfig);
//        loadProperties(config, hibernateConfig);
//        loadMappings(config, hibernateConfig);
//
//        this.sessionFactory =
//                HibernateUtil.createSessionFactory(hibernateConfig);
//    }
//
//    /* HibernateSessionFactory's methods */
//    /**
//     * {@inheritDoc}
//     */
//    public Session getHibernateSession() throws Exception {
//        return getHibernateSession(true);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
////    public Session getHibernateSession(boolean beginTransaction)
////            throws Exception {
////        Connection conn = jdbcFactory != null
////                ? jdbcFactory.getJdbcConnection() : dataSource.getConnection();
////        try {
////            Session session = this.sessionFactory.openSession(conn);
////            try {
////                if ( beginTransaction )
////                    session.beginTransaction();
////                return session;
////            } catch ( Exception e ) {
////                conn = null;
////                releaseHibernateSession(session, beginTransaction);
////                throw e;
////            }
////        } catch ( Exception e ) {
////            if ( conn != null ) {
////                if ( jdbcFactory != null ) {
////                    jdbcFactory.releaseJdbcConnection(conn);
////                } else {
////                    conn.close();
////                }
////            }
////            throw e;
////        }
////    }
//
//    /**
//     * {@inheritDoc}
//     */
////    public void releaseHibernateSession(Session session) throws Exception {
////        releaseHibernateSession(session, true);
////    }
//
//    /**
//     * {@inheritDoc}
//     */
////    public void releaseHibernateSession(Session session, boolean commit)
////            throws Exception {
////        Connection conn = null;
////        try {
////            try {
////                Transaction tx = session.getTransaction();
////                if ( tx != null && tx.isActive() && commit ) {
////                    tx.commit();
////                } else if ( tx != null && tx.isActive() && !commit ) {
////                    tx.rollback();
////                }
////                session.flush();
////            } finally {
////                conn = session.close();
////            }
////        } finally {
////            if ( conn != null ) {
////                if ( jdbcFactory != null ) {
////                    jdbcFactory.releaseJdbcConnection(conn);
////                } else {
////                    conn.close();
////                }
////            }
////        }
////    }
//    /* HibernateSessionFactory's methods */
//}
