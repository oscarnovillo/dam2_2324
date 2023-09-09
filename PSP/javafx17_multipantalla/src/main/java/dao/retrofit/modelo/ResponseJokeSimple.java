package dao.retrofit.modelo;

import lombok.Data;

@Data
public class ResponseJokeSimple {
	private final Flags flags;
	private final boolean safe;
	private final int id;
	private final boolean error;
	private final String category;
	private final String type;
	private final String lang;
	private final String joke;

}
