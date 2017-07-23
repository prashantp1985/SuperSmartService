package com.dhl.chatbot.entities;
import java.io.Serializable;

import com.dhl.chatbot.authentication.Authentication;
import com.dhl.chatbot.authentication.EncrypterDecrypter;
import com.dhl.chatbot.enumeration.UserType;
import com.dhl.chatbot.logging.TraceLogger;

//import org.hibernate.Hibernate;


/**
 * 
 * @author Prashant Padmanabhan
 *
 */
@SuppressWarnings("serial")
public class UserDetails implements Serializable, Cloneable {

	/**
	 * This field indicates user Name
	 */
    private String userName;

    /**
     * This field indicates password
     */
    private byte[] passwordByte;
    
    private String password;

    /**
     * This field indicates user Type
     */
    private UserType userType;
    
    /**
	 * This id constructor for UserDetails.java
	 */
	public UserDetails() {
		super();
	}

	/**
	 * This id constructor for UserDetails.java
	 * @param password
	 * @param userName
	 * @param userType
	 */
	public UserDetails(String password, String userName, UserType userType) throws Exception {
		super();
		this.passwordByte = Authentication.getInstance().encrypt(password);
		this.userName = userName;
		this.userType = userType;
	}

	
    /**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public byte[] getPassword() {
		return passwordByte;
	}

	/**
	 * @return the password
	 */
	public String getPasswordString() throws Exception {
		if (passwordByte == null) {
			setPassword(password);
		}
		return EncrypterDecrypter.getInstance().decrypt(passwordByte);
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(byte[] password) {
		this.passwordByte = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
		String passwordArray[] = password.split(",");
		byte[] passwordByte = new byte[passwordArray.length];
		int count = 0;
		for (String value : passwordArray) {
			passwordByte[count] = Byte.parseByte(value.trim());
			count++;
		}
		this.passwordByte = passwordByte; 
	}
	
	public void setPasswordString(String password) throws Exception {
		this.passwordByte = EncrypterDecrypter.getInstance().encrypt(password);
		this.password = password;
	}
	
	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDetails other = (UserDetails) obj;
        if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
            return false;
        }
        return true;
    }
    
	/**
	 * 
	 * This method is used to compare invoking isEqualTo passed
	 * @param obj
	 * @return boolean
	 * @throws Exception 
	 */
    public boolean isEqualTo(Object obj) throws Exception {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDetails other = (UserDetails) obj;
        if ((this.userName == null) ? (other.userName != null) : ! this.userName.equals(other.userName)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : ! this.getPasswordString().equals(other.getPasswordString())) {
            return false;
        }
        return true;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    /**
     * This method is used to whether user is Admin User
     * @return boolean
     */
    public boolean isAdminUser() {
        boolean isAdmin = false;
        if(UserType.ADMIN_USER.equals(this.userType)) {
            isAdmin = true;
        }
        return isAdmin;
    }

    /**
     * This method is used to whether user is Normal User
     * @return boolean
     */
    public boolean isNormalUser() {
       return !isAdminUser();
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#clone(java.lang.Object)
	 */
	@Override
	public UserDetails clone() {
		UserDetails clonedUserDetails = null; 
		try {
			clonedUserDetails = (UserDetails) super.clone();
//			clonedUserDetails.password = password;
//			clonedUserDetails.setPassword(clonedUserDetails.getPassword());
			clonedUserDetails.userType = userType;
			clonedUserDetails.userName = userName;
		} catch (Exception e) {
			new TraceLogger(UserDetails.class).logError(e);
		}
		return clonedUserDetails;
	}
	
}
