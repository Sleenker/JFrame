import java.io.Serializable;

public class Registro implements Serializable
{
	String nombre, codigo;

	public Registro( String nombre, String codigo )
	{
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String obtenerInformacion()
	{
		return nombre + "  -  " + codigo;
	}
}
