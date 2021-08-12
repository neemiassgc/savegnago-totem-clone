package savegnago.totem.mediator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import savegnago.totem.mediator.service.CustomerMediatorClient;

import java.net.URI;

@Controller
public class CustomerCardController {
	
	@Autowired
	private CustomerMediatorClient customerMediatorClient;

	@PostMapping(path = "/totem/customer-card")
	public ResponseEntity<?> customerCardRedirect(@RequestParam final MultiValueMap<String, String> body) {
		this.customerMediatorClient.mediate(body);
//		final String redirectURL = success
//	   		? "http:/53.67.65.173/totem/nosso_cartao.php"
//			: "/totem/cartoes.html";

		return ResponseEntity
			.status(HttpStatus.PERMANENT_REDIRECT)
			.location(URI.create("http://52.67.65.173/totem/cartao_nosso.php"))
			.build();
	}
}
