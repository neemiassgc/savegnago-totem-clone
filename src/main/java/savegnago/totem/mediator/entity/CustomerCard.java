package savegnago.totem.mediator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "customer_card_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "number", nullable = false, length = 24)
	private String number;

	@Column(name = "password", nullable = false, length = 30)
	private String password;
}
