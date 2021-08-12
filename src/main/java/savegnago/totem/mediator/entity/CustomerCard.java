package savegnago.totem.mediator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "customer_card_tb")
@Getter
@Setter
@NoArgsConstructor
public class CustomerCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "card_number", nullable = false, length = 16)
	private String cardNumber;

	@Column(name = "password", nullable = false, length = 12)
	private String password;

	@Column(name = "data", nullable = true, columnDefinition = "TEXT")
	private String data;

	public CustomerCard(String cardNumber, String password, String data) {
		this.cardNumber = cardNumber;
		this.password = password;
		this.data = data;
	}

	public CustomerCard(String cardNumber, String password) {
		this.cardNumber = cardNumber;
		this.password = password;
	}
}
