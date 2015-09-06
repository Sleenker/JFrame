import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Scanner;

public class Office
{
	FileInputStream FIS;
	ObjectInputStream OIS;
	FileOutputStream FOS;
	ObjectOutputStream OOS;
	File F = new File( "/home/mint/Desktop/Github/JFrame/Hospital_Project/src/com/java/code/Records.data" );
	File backUp = new File( "/home/mint/Desktop/Github/JFrame/Hospital_Project/src/com/java/code/backUp/Records(secured).data" );
	ArrayList < Record > records = new ArrayList <>();

	public static void main( String[] args )
	{
		//new Office().newRecord( "0001", "Jhonny Trejos Barrios", "29", "Barranquilla", "2 Months" );
		//new Office().newRecord( "0002", "Luis De La Asuncion", "26", "Barranquilla", "4 Years" );
		//new Office().newRecord( "0003", "Unknown Name", "Unknown Age", "Unknown City", "Unknown Oldest" );

		//System.out.print( "Type in record position: " );
		//new Office().selectRecord( new Scanner( System.in ).nextInt() );

		new Office().showAllRecords();

		//System.out.print( "Type in record position to delete: " );
		//new Office().deleteRecord( new Scanner( System.in ).nextInt() );

		//new Office().deleteDuplicateRecords();

		//new Office().deleteAllRecords();

		//Type in 'view' to see backup content or 'recover' to import all records.
		//new Office().recover( "view" );
	}

	public void selectRecord( int value )
	{
		loadData();

		if( ( value > 0 ) && ( value <= records.size() ) )
		{
			System.out.println( records.get( value - 1 ).getRecordInfo() );

			System.err.println( "Showing record " + value + " of " + records.size() );
		}
		else
		{
			System.err.println( "Error01.\nInvalid range, to select a record retype a value between 1 and " + records.size() );
		}
	}

	public void showAllRecords()
	{
		loadData();

		for( Record R : records )
		{
			System.out.println( R.getRecordInfo() );
		}

		System.err.println( "Showing " + records.size() + " records" );
	}

	public void deleteRecord( int value )
	{
		loadData();

		if( ( value > 0 ) && ( value <= records.size() ) )
		{

			try
			{
				System.out.println( records.get( value - 1 ).getRecordInfo() );

				FOS = new FileOutputStream( F );
				OOS = new ObjectOutputStream( FOS );

				records.remove( value - 1 );

				OOS.writeObject( records );

				OOS.close();
				FOS.close();

				System.err.println( "This record has been deleted!" );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println( "Error02.\nInvalid range, to delete a record retype a value between 1 and " + records.size() );
		}
	}

  	public void deleteDuplicateRecords()
	{
		loadData();

		int deleted = 0;

		for( int i = 0; i < records.size() - 1; i ++ )
		{
			for( int j = i + 1; j < records.size(); j ++ )
			{
				if( records.get( i ).getRecordInfo().equals( records.get( j ).getRecordInfo() ) )
				{
					records.remove( j );
					deleted ++;
				}
			}
		}

		if( deleted > 0 )
		{
			try
			{
				FOS = new FileOutputStream( F );
				OOS = new ObjectOutputStream( FOS );

				OOS.writeObject( records );

				OOS.close();
				FOS.close();

				System.err.println( deleted + " duplicate records has been deleted!" );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println( "No duplicate records has been found." );
		}
	}

	public void deleteAllRecords()
	{
		loadData();

		try
		{
			int total = records.size();

			FOS = new FileOutputStream( F );
			OOS = new ObjectOutputStream( FOS );

			records.removeAll( records );

			OOS.writeObject( records );

			OOS.close();
			FOS.close();

			System.err.println( "All records has been deleted!\nTotal: " + total );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings( "unchecked" )
	public void recover( String mode )
	{
		if( backUp.exists() )
		{
			try
			{
				FIS = new FileInputStream( backUp );
				OIS = new ObjectInputStream( FIS );

				records = ( ArrayList < Record > ) OIS.readObject();

				OIS.close();
				FIS.close();

				boolean flag = false;

				for( int i = 0; i < records.size() - 1; i ++ )
				{
					for( int j = i + 1; j < records.size(); j ++ )
					{
						if( records.get( i ).getRecordInfo().equals( records.get( j ).getRecordInfo() ) )
						{
							records.remove( j );
							flag = true;
						}
					}
				}

				if( flag )
				{
					FOS = new FileOutputStream( backUp );
					OOS = new ObjectOutputStream( FOS );

					OOS.writeObject( records );

					OOS.close();
					FOS.close();
				}

				for( Record R : records )
				{
					System.out.println( R.getRecordInfo() );
				}

				System.err.println( "Content of this copy: " + records.size() + " records." );

				if( mode.equals( "recover" ) )
				{
					FOS = new FileOutputStream( F );
					OOS = new ObjectOutputStream( FOS );

					OOS.writeObject( records );

					OOS.close();
					FOS.close();

					System.err.println( "Records has been recovered.\nTotal: " + records.size() );
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println( "No backups are available." );
		}
	}

	@SuppressWarnings( { "unchecked", "ResultOfMethodCallIgnored" } )
	public void newRecord( String code, String name, String age, String city, String oldest )
	{
		try
		{
			if( ! backUp.exists() )
			{
				new File( backUp.getParent() ).mkdirs();
 			}

			if( F.exists() )
			{
				FIS = new FileInputStream( F );
				OIS = new ObjectInputStream( FIS );

				records = ( ArrayList < Record > ) OIS.readObject();

				OIS.close();
				FIS.close();
			}

			FOS = new FileOutputStream( F );
			OOS = new ObjectOutputStream( FOS );

			records.add( new Record( code, name, age, city, oldest ) );

			OOS.writeObject( records );

			OOS.close();
			FOS.close();

			//SAVING BACKUP...
			if( backUp.exists() )
			{
				FIS = new FileInputStream( backUp );
				OIS = new ObjectInputStream( FIS );

				records = ( ArrayList < Record > ) OIS.readObject();

				OIS.close();
				FIS.close();
			}

			FOS = new FileOutputStream( backUp );
			OOS = new ObjectOutputStream( FOS );

			records.add( new Record( code, name, age, city, oldest ) );

			OOS.writeObject( records );

			OOS.close();
			FOS.close();
			//BACKUP SAVED!

			System.err.println( "Successfully saved on: " + F.getParent() );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings( { "unchecked", "ResultOfMethodCallIgnored" } )
	public void loadData()
	{
		try
		{
			if( ! backUp.exists() )
			{
				new File( backUp.getParent() ).mkdirs();
			}

			if( F.exists() )
			{
				FIS = new FileInputStream( F );
				OIS = new ObjectInputStream( FIS );
	
				records = ( ArrayList < Record > ) OIS.readObject();
	
				OIS.close();
				FIS.close();

				if( records.isEmpty() )
				{
					System.err.println( "Patients file is empty." );
					System.exit( 0 );
				}
			}
			else
			{
				System.err.println( "Patients file doesn't exists." );
				System.exit( 0 );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
