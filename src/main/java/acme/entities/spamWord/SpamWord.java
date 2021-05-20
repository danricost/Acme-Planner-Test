package acme.entities.spamWord;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
	public class SpamWord extends DomainEntity {

		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------		
		protected String			palabraSpam;
				
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

	}