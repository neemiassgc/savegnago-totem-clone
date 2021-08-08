package savegnago.totem.mediator.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceClient {

	public ResponseEntity<byte[]> findResource(final String url, final HttpHeaders headers) {
		final RestTemplate client = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return client.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
	}
}
