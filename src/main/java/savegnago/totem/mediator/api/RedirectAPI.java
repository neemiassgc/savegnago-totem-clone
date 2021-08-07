package savegnago.totem.mediator.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@Controller
public class RedirectAPI {

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

	@GetMapping(path = "/totem/images/{image}")
	public ResponseEntity<?> redirectImages(@PathVariable(name = "image") final String image) {
		final String redirectURL = String.format("http://%s/totem/images/%s", this.ORIGIN_HOST, image);
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = {"/totem/aniversario", "/totem/aniversario2021"})
	public ResponseEntity<?> birthdate() {
		final String redirectURL = String.format("/totem/aniversario2021/index.html", this.ORIGIN_HOST);
		return this.defaultResponse(redirectURL);
	}

	private ResponseEntity<?> defaultResponse(final String redirectURL) {
		return ResponseEntity
		   .status(HttpStatus.PERMANENT_REDIRECT)
		   .location(URI.create(redirectURL))
		   .build();
	}
}
