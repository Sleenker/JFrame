import java.util.Scanner;

public class HelloWorld
{
	public static void main( String[] args )
	{
		System.out.print( "Please type in your name: " );

		new HelloWorld().showMessage( new Scanner( System.in.nextLine() ) );
	}

	public String showMessage( String yourName )
	{
		return "Hello World!!\nWelcome to Java " + yourName;
	}
}
