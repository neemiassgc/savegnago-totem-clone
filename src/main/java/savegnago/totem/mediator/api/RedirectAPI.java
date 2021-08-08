package savegnago.totem.mediator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import savegnago.totem.mediator.service.ResourceClient;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
public class RedirectAPI {

	@Autowired
	private ResourceClient resourceClient;

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

	@GetMapping(path = {"/totem/aniversario", "/totem/aniversario2021"})
	public ResponseEntity<?> birthdate() {
		final String redirectURL = String.format("/totem/aniversario2021/index.html", this.ORIGIN_HOST);
		return this.defaultResponse(redirectURL);
	}

	@GetMapping(path = {"/totem/{dir:(?:img|images|css)}/{file}.{ext}"})
	public ResponseEntity<byte[]> getResource(@RequestHeader final HttpHeaders headers) {
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.scheme("http")
			.host("52.67.65.173")
			.port(80).build(true).toUriString();

		return this.resourceClient.findResource(uriString, headers);
	}

	private ResponseEntity<?> defaultResponse(final String redirectURL) {
		return ResponseEntity
		   .status(HttpStatus.PERMANENT_REDIRECT)
		   .location(URI.create(redirectURL))
		   .build();
	}
}
