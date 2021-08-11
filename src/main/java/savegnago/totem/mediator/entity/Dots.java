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
@AllArgsConstructor
public class Dots {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)")
	private String cpf;

	@Column(name = "password", nullable = false, length = 20)
	private String password;

	@Column(name = "phone_number", nullable = true, length = 18)
	private String phoneNumber;
}
