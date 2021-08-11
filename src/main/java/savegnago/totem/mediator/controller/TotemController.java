package savegnago.totem.mediator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import savegnago.totem.mediator.service.TotemClient;

@RestController
@RequestMapping(path = "/totem/api")
public class TotemController {

	@Autowired
	private TotemClient totemClient;

	@PostMapping(path = "/LoginCA/Login")
	public ResponseEntity<String> loginCa(
		@RequestParam MultiValueMap<String, String> params,
		@RequestHeader HttpHeaders headers
	) {
		final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cpfPortador", params.get("cpfPortador").get(0));

		params.remove("cpfPortador");

		return totemClient.login(params, body, headers);
	}

	@PostMapping(path = "/LoginCA/Logout")
	public ResponseEntity<String> logout(
		@RequestParam MultiValueMap<String, String> params,
		@RequestHeader HttpHeaders headers
	) {
		return totemClient.logout(params, headers);
	}

	@GetMapping(path = "/PortadorCA/Historico")
	public ResponseEntity<String> history(
		@RequestParam MultiValueMap<String, String> params,
		@RequestHeader HttpHeaders headers
	) {
		return totemClient.history(params, headers);
	}

	@GetMapping(path = "/CartoesCA/ImprimirSaldo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> printBalance(
		@RequestParam MultiValueMap<String, String> params,
		@RequestHeader HttpHeaders headers
	) throws JsonProcessingException {
		return totemClient.printBalance(params, headers);
	}

	@PostMapping(path = "/CartoesCA/AlterarSenha")
	public ResponseEntity<String> blockCard(
		@RequestParam MultiValueMap<String, String> params,
		@RequestHeader HttpHeaders headers
	) {
		final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("senhaNova", params.get("senhaNova").get(0));
		body.add("senhaAntiga", params.get("senhaAntiga").get(0));

		params.remove("senhaAntiga");
		params.remove("senhaNova");

		return totemClient.changePassword(params, body, headers);
	}
}
