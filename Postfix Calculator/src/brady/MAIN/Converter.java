package brady.MAIN;

import java.util.List;

public class Converter {
	
    private static boolean isDigit(String s) {
        char[] sArray = s.toCharArray();
        for (char c: sArray) {
        	if (Character.isDigit(c)) {
        		return true;
        	}
        }
        return false;
    }

    private static int precedence(String s1) {
    	 if(s1.contains("+") || s1.contains("-"))
    		 return 1;
    		 
    	 else if(s1.contains("*") || s1.contains("/"))
    		 return 2;    
    	 
    	 else if (s1.contains("^"))
    		 return 3;
    	 
    		 return 0;
    }
    

    public static String convertToPostfix(List<String> infix) {
        ArrayStack<String> operators = new ArrayStack<String>();
        ArrayStack<String> postFix = new ArrayStack<String>();
        
        for (String s: infix) {
        	if (s.contains("(")) {
        		operators.push(s);
        	} else if (isDigit(s))  {
        		postFix.push(s);
        		
        	} else if (s.contains(")")) {
        		while (operators.top().contains("(") == false){
        			
	        		String op = operators.pop();
	        		
	        		String first = postFix.pop();
	        		String second = postFix.pop();
	        		
	        		String new_postFix = second+ " " + first+ " " + op + " ";
	        		
	        		postFix.push(new_postFix);
        	
        		}
        		operators.pop();
        	}
        	
        	else if (isDigit(s) == false) {
        		
        		while(operators.size()>0 && operators.top()!="(" && 
        				precedence(s)<=precedence(operators.top())) {
        			
        			String op = operators.pop();
        			
        			String first = postFix.pop();
        			String second = postFix.pop();
        			
        			String new_postFix = second+ " " + first+ " " + op + " ";
        			
        			postFix.push(new_postFix);
        		}
        		operators.push(s);
        	}
        }
        
        while(operators.size() > 0) {
        	
			String op = operators.pop();
			
			String first = postFix.pop();
			String second = postFix.pop();
			
			String new_postFix = second+ " " + first+ " " + op + " ";
			
			postFix.push(new_postFix);
        }
        
        return postFix.pop().toString();      
    }
}