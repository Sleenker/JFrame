import java.util.Scanner;

public class Console
{
	public static void main( String[] args )
	{
		System.out.print( "Please type in your name: " );
		System.out.println( new Console().showName( new Scanner( System.in ).nextLine() ) );
	}

	public String showName( String name )
	{
		return "Nice to meet you " + name + "!";
	}
}
