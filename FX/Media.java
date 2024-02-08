package application;

//Class for media

public class Media {
	protected String title;
	protected int copiesAvailable;
	protected String code;

	
	//Setters and Getters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable)
	{
		this.copiesAvailable = copiesAvailable;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean equals(Media o)// equals method 
	{
		if(o.copiesAvailable - copiesAvailable == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Media [title=" + title + ", copiesAvailable=" + copiesAvailable + ", code=" + code + "]";
	}

	
}
