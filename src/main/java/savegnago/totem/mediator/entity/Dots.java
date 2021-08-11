package savegnago.totem.mediator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dots_tb")
@Getter
@Setter
@NoArgsConstructor
public class Dots {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)")
	private String cpf;

	@Column(name = "phone_number_suffix", nullable = true, length = 4)
	private String phoneNumberSuffix;

	public Dots (String cpf, String phoneNumberSuffix) {
		this.cpf = cpf;
		this.phoneNumberSuffix = phoneNumberSuffix;
	}
}
