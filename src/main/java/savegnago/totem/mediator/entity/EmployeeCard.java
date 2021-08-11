package savegnago.totem.mediator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employee_card_tb")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)")
	private String cpf = "";

	@Column(name = "name", nullable = false, length = 100)
	private String name = "";

	@Column(name = "data", nullable = false, columnDefinition = "TEXT")
	private String data = "";

	public EmployeeCard (String cpf, String name, String data) {
		this.cpf = cpf;
		this.name = name;
		this.data = data;
	}
}
