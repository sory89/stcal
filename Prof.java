public class Prof extends Personne
{
	protected String uv;

	public Prof(String nom, String prenom, String uv)
	{
		super(nom, prenom);
		this.uv = uv;
	}

	public void getUv()
	{
		return "uv : " + this.uv;
	}

	public String setUv(String uv)
	{
		this.uv = uv;
	}
}
