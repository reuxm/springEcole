package springEcole.bean;

import java.util.Arrays;
import java.util.List;

public enum Sexe {
	HOMME, FEMME;

	public static List<Sexe> all() {
		return Arrays.asList( new Sexe[] {
			HOMME, FEMME	
		} );
	}
}
