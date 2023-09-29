package dao.retrofit.modelo;

import lombok.Data;

@Data
public class Flags{
	private boolean sexist;
	private boolean explicit;
	private boolean religious;
	private boolean nsfw;
	private boolean political;
	private boolean racist;
}
