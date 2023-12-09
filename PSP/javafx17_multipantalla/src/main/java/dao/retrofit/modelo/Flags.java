package dao.retrofit.modelo;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class Flags{
	private boolean sexist;
	private boolean explicit;
	private boolean religious;
	private boolean nsfw;
	private boolean political;
	private boolean racist;


}
