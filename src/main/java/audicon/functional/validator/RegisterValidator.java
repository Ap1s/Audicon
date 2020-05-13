package audicon.functional.validator;

public  class RegisterValidator {
	public static int validateRegistration(final String username, final String password, final String pw_retyped) {
		// check username 
		if(username.length() > 50 ) {
			return -1;
		}
		
		if(password.length() < 8 ) {
			return -2;
		}
		
		if(!password.equals(pw_retyped)) {
			return -3;
		}
		
		return 1;
	}
}
