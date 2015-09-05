import java.io.Serializable;

public class Record implements Serializable
{
	String code, name, age, city, oldest;

	public Record( String code, String name, String age, String city, String oldest )
	{
		this.code = code;
		this.name = name;
		this.age = age;
		this.city = city;
		this.oldest = oldest;
	}

	public String getRecordInfo()
	{
		return code + "  -  " + name + "  -  " + age + "  -  " + city + "  -  " + oldest;
	}
}
