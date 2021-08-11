package savegnago.totem.mediator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import savegnago.totem.mediator.entity.EmployeeCard;
import savegnago.totem.mediator.repository.EmployeeCardRepo;

import java.net.URI;

import java.util.List;

@Service
public class TotemClient {

	@Autowired
	private EmployeeCardRepo employeeCardRepo;

	private static final String HOST = "52.67.65.173";

	private static final String URL_BASE = String.format("http://%s/totem/api", HOST);

	public ResponseEntity<String> login(
		final MultiValueMap<String, String> params,
		final MultiValueMap<String, String> body,
		final HttpHeaders headers
	) {
		maskHeaders(headers);
		headers.replace("referer", List.of(String.format("http://%s/totem/cartao_alimentacao.php", this.HOST)));

		final URI uri =
			UriComponentsBuilder.fromHttpUrl(URL_BASE+"/LoginCA/Login")
				.port(80).queryParams(params).build(true).toUri();

		final RestTemplate client = new RestTemplate();
		final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

		final ResponseEntity<String> response = client.postForEntity(uri, httpEntity, String.class);

		try {
			final JsonNode root = new ObjectMapper().readTree(response.getBody());
			if (root.get("status").get("success").asBoolean()) {
				final JsonNode owner = root.get("result").get("values").get("portador");
				final String cpf = owner.get("cpf").asText();
				if (!this.employeeCardRepo.existByCpf(cpf)) {
					final String name = owner.get("nome").asText();
					final EmployeeCard employeeCard = new EmployeeCard(cpf, name, root.get("result").get("values").asText());

					employeeCardRepo.save(employeeCard);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ResponseEntity<String> logout(
		final MultiValueMap<String, String> params,
		final HttpHeaders headers
	) {
		maskHeaders(headers);
		headers.replace("referer", List.of(String.format("http://%s/totem/cartao-alimentacao/extratos.html", this.HOST)));

		final URI uri =
			UriComponentsBuilder.fromHttpUrl(URL_BASE+"/LoginCA/Logout")
				.port(80).queryParams(params).build(true).toUri();

		final RestTemplate client = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return client.postForEntity(uri, httpEntity, String.class);
	}

	public ResponseEntity<String> history(
		final MultiValueMap<String, String> params,
		final HttpHeaders headers
	) {
		maskHeaders(headers);
		headers.replace("referer", List.of(String.format("http://%s/totem/cartao-alimentacao/extratos.html", this.HOST)));

		final URI uri =
			UriComponentsBuilder.fromHttpUrl(URL_BASE+"/PortadorCA/Historico")
				.port(80).queryParams(params).build(true).toUri();

		final RestTemplate client = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return client.exchange(uri, HttpMethod.GET, httpEntity, String.class);
	}

	public ResponseEntity<String> printBalance(
		final MultiValueMap<String, String> params,
		final HttpHeaders headers
	) throws JsonProcessingException {
		maskHeaders(headers);
		headers.replace("referer", List.of(String.format("http://%s/totem/cartao-alimentacao/extratos.html", this.HOST)));

		final URI uri =
			UriComponentsBuilder.fromHttpUrl(URL_BASE+"/CartoesCA/ImprimirSaldo")
				.port(80).queryParams(params).build(true).toUri();

		final RestTemplate client = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		final ResponseEntity<String> responseEntity = client.exchange(uri, HttpMethod.GET, httpEntity, String.class);

		return new ResponseEntity<>(
			responseEntity.getBody(),
			responseEntity.getHeaders(),
			responseEntity.getStatusCode()
		);
	}

	public ResponseEntity<String> changePassword(
		final MultiValueMap<String, String> params,
		final MultiValueMap<String, String> body,
		final HttpHeaders headers
	) {
		maskHeaders(headers);
		headers.replace("referer", List.of(String.format("http://%s/totem/cartao-alimentacao/cartoes.html", this.HOST)));

		final URI uri = UriComponentsBuilder.fromHttpUrl(URL_BASE + "/CartoesCA/AlterarSenha")
			.port(80).queryParams(params).build(true).toUri();

		final RestTemplate client = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

		return client.postForEntity(uri, httpEntity, String.class);
	}

	private void maskHeaders(final HttpHeaders headers) {
		headers.replace("host", List.of(this.HOST));
		headers.replace("Origin", List.of("http://"+this.HOST));
	}
}
