package savegnago.totem.mediator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import savegnago.totem.mediator.entity.Dots;
import savegnago.totem.mediator.repository.DotsRepo;
import savegnago.totem.mediator.service.ResourceClient;

import java.net.URI;

@SuppressWarnings("unused")
@Controller
public class RedirectController {

	@Autowired
	private ResourceClient resourceClient;

	@Autowired
	private DotsRepo dotsRepo;

	private static final String ORIGIN_HOST = "52.67.65.173";

	@GetMapping(path = {"/", "/totem", "", "/index", "/home"})
	public ResponseEntity<?> index() {
		final String redirectURL = "/totem/index.html";
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = "/totem/netpoints")
	public ResponseEntity<?> netPoints() {
		final String redirectURL = "/totem/netpoints_inicial.html";
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(
		path = {"/totem/cartao-alimentacao/index", "/totem/cartao-alimentacao", "/totem/cartao-alimentacao/home"})
	public ResponseEntity<?> foodCard() {
		final String redirectURL = "/totem/cartao-alimentacao/index.html";
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = {"/aniversario", "/totem/aniversario"})
	public ResponseEntity<?> birthdate() {
		final String redirectURL = "/totem/aniversario.html";
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = {"/aniversario2021", "/totem/aniversario2021"})
	public ResponseEntity<?> birthdate2021() {
		final String redirectURL = "/totem/aniversario2021/index.html";
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = {"/totem/**/{a:(?:img|images|css)}/*"})
	public ResponseEntity<byte[]> getResource(@RequestHeader final HttpHeaders headers) {
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.scheme("http")
			.host("52.67.65.173")
			.port(80).build(true).toUriString();

		return this.resourceClient.findResource(uriString, headers);
	}

	@PostMapping(path = "/totem/customer-card")
	public ResponseEntity<?> customerCardRedirect(@RequestParam final MultiValueMap<String, String> params) {
		params.forEach((key, value) -> {
			System.out.println(key+" -> "+String.join(", ", value));
		});
		return defaultResponse(String.format("http://%s/totem/cartao_nosso.php", ORIGIN_HOST));
	}

	@PostMapping(path = "/totem/netpoints/{path:(?:query|password)}")
	public ResponseEntity<?> netPointsRedirect(
		@RequestParam final MultiValueMap<String, String> params,
		@PathVariable(name = "path") final String path
	) {
		if (params.containsKey("cpf")) {
			final String cpf = params.get("cpf").get(0);

			this.dotsRepo.findByCpf(cpf).ifPresentOrElse(dots -> {
				if (params.containsKey("phone"))
					dots.setPhoneNumberSuffix(params.get("phone").get(0));

				this.dotsRepo.save(dots);
			}, () -> {
				final String phoneSuffix = params.containsKey("phone") ? params.get("phone").get(0) : null;
				this.dotsRepo.save(new Dots(cpf, phoneSuffix));
			});
		}

		return defaultResponse(String.format(
			"http://%s/totem/%s.php", ORIGIN_HOST,
			path.equals("query") ? "netpoints" : "netpoints_senha"
		));
	}


	private ResponseEntity<?> defaultResponse(final String redirectURL) {
		return ResponseEntity
		   .status(HttpStatus.PERMANENT_REDIRECT)
		   .location(URI.create(redirectURL))
		   .build();
	}
}
