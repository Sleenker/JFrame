import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;

public class Frame extends JFrame
{
	JPanel contentPane;
	JButton button;

	public static void main( String[] args )
	{
		new Frame().setVisible( true );
	}

	public Frame()
	{
		setTheme();
		setTitle( "Basic Java Frame" );
		setSize( 450, 360 );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 2, 2, 2, 2 ) );
		contentPane.setLayout( null );
		contentPane.setBackground( new Color( 60, 63, 65 ) );
		setContentPane( contentPane );

		button = new JButton( "Close" );
		button.setBounds( 100, 100, 90, 25 );
		button.setFocusable( false );
		button.addActionListener( event -> System.exit( 0 ) );
		getContentPane().add( button );
		getRootPane().setDefaultButton( button );
	}

	public void setTheme()
	{
		try
		{
			UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
