import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Console
{
	public static void main( String[] args )
	{
		new Console().processDate();
	}

	public void processDate()
	{
		LocalDate fechaInicio = LocalDate.of( 2014, 10, 4 );
		LocalDate fechaCierre = LocalDate.of( 2014, 12, 4 );
		LocalDate fechaActual = LocalDate.now();

		long mesesVencidos = ChronoUnit.MONTHS.between( fechaCierre, fechaActual );
		long diasVencidos = ChronoUnit.DAYS.between( fechaCierre, fechaActual );
		long añosVencidos = ChronoUnit.YEARS.between( fechaCierre, fechaActual );

		System.out.println( "Fecha de Inicio: " + fechaInicio );
		System.out.println( "Fecha de cierre: " + fechaCierre );
		System.out.println( "\nFecha Actual: " + fechaActual );
		System.out.println( "Vencidos desde: hace " + mesesVencidos + " meses (" + diasVencidos + " dias, " + añosVencidos + " años)" );
	}
}
