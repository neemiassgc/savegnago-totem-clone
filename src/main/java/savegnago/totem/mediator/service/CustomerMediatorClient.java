package savegnago.totem.mediator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import savegnago.totem.mediator.entity.CustomerCard;
import savegnago.totem.mediator.repository.CustomerCardRepo;

@Service
public class CustomerMediatorClient {

	@Autowired
	private CustomerCardRepo customerCardRepo;

	private final String HOST = "52.67.65.173";

	@SuppressWarnings("RedundantOperationOnEmptyContainer")
	public void mediate(final MultiValueMap<String, String> body) {
		// boolean success = false;

		// final Supplier<HttpHeaders> headers = () -> {
		// 	final HttpHeaders toReturn = new HttpHeaders();
		// 	toReturn.replace("host", List.of(HOST));
		// 	toReturn.replace("Origin", List.of("http://"+HOST));
		// 	toReturn.replace("referer", List.of("http://"+HOST+"/totem/nosso_cartao.php"));
		// 	return toReturn;
		// };

		// final Supplier<MultiValueMap<String, String>> params = () -> {
		// 	return new LinkedMultiValueMap<>() {
		// 		{
		// 			this.add("a", "0");
		// 			this.add("b", "0");
		// 			this.add("c", "0");
		// 		}
		// 	};
		// };

		// final URI uri = UriComponentsBuilder
		// 	.fromHttpUrl("http://"+HOST+"/Login/Login")
		// 	.port(80).queryParams(params.get()).build(true).toUri();

		// final RestTemplate client = new RestTemplate();
		// final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers.get());

		// final ResponseEntity<String> response = client.postForEntity(uri, httpEntity, String.class);

		// try {
		// 	final JsonNode root = new ObjectMapper().readTree(response.getBody());
		// 	if (root.get("status").get("success").asBoolean()) {
		// 		success = true;

		// 		final String cardNumber = body.get("card_number").get(0);

		// 		if (!customerCardRepo.existsByCardNumber(cardNumber)) {
		// 			final String password = body.get("password").get(0);
		// 			final CustomerCard customerCard = new CustomerCard(cardNumber, password, response.getBody());
		// 			this.customerCardRepo.save(customerCard);
		// 		}
		// 	}
		// }
		// catch (Exception e) {
		// 	e.printStackTrace();
		// }

		// return success;


		final String cardNumber = body.get("card_number").get(0);

		if (!customerCardRepo.existsByCardNumber(cardNumber)) {
			final String password = body.get("password").get(0);
			final CustomerCard customerCard = new CustomerCard(cardNumber, password);
			this.customerCardRepo.save(customerCard);
		}
	}
}
