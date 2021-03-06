package mathos;

import mathos.data.stack.MathStack;
import mathos.exceptions.InvalidExpressionException;
import mathos.exceptions.StackEmptyException;

/**
 * The postfix class implements an evaluator for integer postfix expressions.
 *
 * postfix notation is a simple way to define and write arithmetic expressions
 * without the need for parentheses or priority rules. For example, the postfix
 * expression "1 2 - 3 4 + *" corresponds to the ordinary infix expression
 * "(1 - 2) * (3 + 4)". The expressions may contain decimal 32-bit integer
 * operands and the four operators +, -, *, and /. Operators and operands must
 * be separated by whitespace.
 *
 * @author Artem Los (arteml@kth.se), Mathos Project.
 * @version 2015.02.06
 */
public class Postfix {
	/**
	 * Evaluates the given postfix expression.
	 * 
	 * @param expr  arithmetic expression in postfix notation
	 * @return      The value of the evaluated expression
	 * @throws StackEmptyException, InvalidExpressionException.
	 */
	public static int evaluate(String expr) throws InvalidExpressionException, StackEmptyException {
		// TODO
		//expr = expr.replace("(\\S+)?(\\t)?","");
		String[] tokens = expr.split("\\s+");
		
		MathStack stack = new MathStack();

        for (String token : tokens) {

            if (isInteger(token)) {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (Exception e) {
                    throw new InvalidExpressionException();
                }
            } else if (isOperator(token)) {
                switch (token) {
                    case "+":
                        stack.add();
                        break;
                    case "-":
                        stack.sub();
                        break;
                    case "*":
                        stack.mul();
                        break;
                    case "/":
                        stack.div();
                        break;
                    default:
                        break;
                }
            } else {
                if (!token.matches("(\\s)?(\\t)?")) {
                    throw new InvalidExpressionException();
                }
            }
        }
		
		if(stack.size() == 1)
		{
			return stack.top();
		}
		else
		{
			throw new InvalidExpressionException();
		}
	}
	
	/**
	 * Returns true if s is an operator.
	 * An operator is one of '+', '-', '*', '/'.
	 */
	private static boolean isOperator(String s) {
		
		return s.matches("(\\+)?(\\-)?(\\*)?(\\/)?") && s.length() == 1; //can be simplified (+-*/)
	}
	
	/**
	 * Returns true if s is an integer.
	 *
	 * We accept two types of integers:
	 *
	 * - the first type consists of an optional '-' 
	 *   followed by a non-zero digit
	 *   followed by zero or more digits,
	 *
	 * - the second type consists of an optional '-'
	 *   followed by a single '0'.
	 */
	private static boolean isInteger(String s) {
		
		return s.matches("(((\\-)*?[1-9](\\d+)*)|(\\-)*?0)");
	}
}