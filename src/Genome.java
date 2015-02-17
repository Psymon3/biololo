import lombok.Getter;
import lombok.Setter;

public class Genome 
{
	// The genome's id
	@Getter @Setter private long id;
	
	// The organism or name of the genome
	@Getter @Setter private String organism;
	
	// The kingdom of the genome
	@Getter @Setter private String kingdom;
	
	// The group of the genome
	@Getter @Setter private String group;
	
	// The subgroup of the genome
	@Getter @Setter private String subgroup;
	
	// The date of update of this genome
	@Getter @Setter private String updateDate;
	
	public Genome()
	{
		organism = null;
		kingdom = null;
		group = null;
		subgroup = null;
		updateDate = null;
	}
}
