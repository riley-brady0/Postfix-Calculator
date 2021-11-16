package brady.MAIN;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

public class PostfixCalculator {
	
	static NumberFormat formatter = new DecimalFormat("#0.00"); 
	static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		//Loop
		System.out.println("Would you like to enter an expression? (Y or N): ");
		//System.out.println("[Press Enter Twice]");
		while (true) {
			String YN = reader.next();
			if (YN.toLowerCase().equals("n")) {
				System.out.println("Goodbye!");
				return;
			} else if (!YN.toLowerCase().equals("y") & !YN.toLowerCase().equals("n")) {
				System.out.println("Y or N:");
				continue;
			} else {
				break;
			}
		}
		
		//Entering Infix Equation
		System.out.println("Type your infix expression: ");
		String infix = reader.next(); 
		
		//Parsing Equation
		char[] infixArray = infix.toCharArray();
		System.out.println("Converted to Postfix: " 
		+ Converter.convertToPostfix(ParserHelper.parse(infixArray)));
		
		double answer = evaluate(Converter.convertToPostfix(ParserHelper.parse(infixArray)));
		//Printing Results
		System.out.println("Answer is: " + formatter.format(answer));
		System.out.println();
		
	    main(args);
		reader.close();
		
	}
	
	
	static boolean isOperator(char ch)
	  {
	      if(ch == '+' || ch == '-' || ch == '*' || ch == '/')
	      return true;
	      
	      return false;
	  }
	    
	private static final String OPERATORS = "^*/+-";    // "$" = exponentiation

	   public static double evaluate(String postfix)
	   {
	      Scanner scan = new Scanner(postfix) ;    

	      ArrayStack<Double> stack = new ArrayStack<Double>();
		while ( scan.hasNext() )                 // while more symbols in string
	      {
	         String symbol = scan.next();          // get next symbol

	         if (!OPERATORS.contains(symbol))   
	         {
	            // convert to double and place on stack
	            double number = Double.parseDouble(symbol);
	            stack.push(number);
	         } 
	         else                                  // must be operator
	         {


	            double opnd2 = stack.pop();       
	            double opnd1 = stack.pop();       

	            if (symbol.equals("^")) 
	               stack.push(Math.pow(opnd1, opnd2));
	            else if (symbol.equals("*")) 
	               stack.push(opnd1 * opnd2);
	            else if (symbol.equals("/")) 
	               stack.push(opnd1 / opnd2);
	            else if (symbol.equals("+")) 
	               stack.push(opnd1 + opnd2);
	            else // must be "-"
	            
	               stack.push(opnd1 - opnd2);
	         }
	      }

	      // final result is on top of stack - pop and return
	      return stack.pop();
	   }
	 
}   

