package CarRental;

public class TypeChecking {
	
	public static boolean checkInt(String str) {
		try  
		  {  
		    double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true; 
	}
	
	public static boolean checkLetter(String str) {
		   for (int i = 0; i < str.length(); i++) {
			   try  
				  {  
				    String letter  = "" + str.charAt(i);
				    double d = Double.parseDouble(letter);
				    return false;
				  }  
				  catch(NumberFormatException nfe)  
				  {
				  }
		   }  
		  return true; 
	}
}
