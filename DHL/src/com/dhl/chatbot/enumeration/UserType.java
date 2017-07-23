package com.dhl.chatbot.enumeration;



/**
 * 
 * @author Prashant Padmanabhan
 *
 */

public enum UserType {

	ADMIN_USER("A"),

	NORMAL_USER("N");

	private final String value;

	UserType(String value) {
		this.value = value;
	}
	

	public String getValue() {
		return value;
	}
	 /* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value;
	}
	
	public static class EnumUserType extends EnumCustomType<UserType> {
		public EnumUserType() { 
			super(UserType.class); 
		} 
		
	}
	
}




//public class UserType extends EnumUserType<UserType.User> {
//	
//    public static final UserType ADMIN_USER = new UserType(User.ADMIN_USER.getValue());
//	
//	public static final UserType NORMAL_USER = new UserType(User.NORMAL_USER.getValue());
//	
//	private String value = null;
//		 
//	public String getValue() { 
//		return value; 
//	}
//	
//	public enum User {
//		
//		ADMIN_USER("A"),
//		
//		NORMAL_USER("N");
//	  
//		private final String value;
//		
//		User(String value) {
//	        this.value = value;
//	    }
//	  
//		public String getValue() { 
//			return value; 
//		}
//	}
//	 public UserType() { 
//	        super(UserType.User.class); 
//	 } 
//	 
//	 public UserType(String value) { 
//	        super(UserType.User.class); 
//	        this.value = value;
//	 } 
//	 
//	 /* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof UserType) {
//			UserType passedUserType = (UserType)obj;
//			this.value.equals(passedUserType.
//		}
//		return false;
//	}
//    
//}


