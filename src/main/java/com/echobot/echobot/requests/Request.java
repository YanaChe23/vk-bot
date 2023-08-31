package com.echobot.echobot.requests;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.uri.Uri;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
abstract class Request {
	private HttpClient httpClient;
	@Autowired
	private Uri uri;
	private HttpClient getHttpClient() {
		if (httpClient == null) httpClient = HttpClient.newHttpClient();
		return httpClient;
	}
	public HttpResponse<String> makeRequest(String action, VkEvent vkEvent, String[] headers, Duration timeout,
											HttpClient.Version version)  {
		String uriComponents = uri.buildUri(action, vkEvent);
		HttpRequest httpRequest = createRequest(uriComponents, headers, timeout, version);
		HttpResponse<String> response =  sendRequest(httpRequest);
		return response;
	}
	abstract HttpRequest createRequest(@NonNull String uri, String[] headers, Duration timeout,
										   HttpClient.Version version);
	protected HttpRequest.Builder addSettingsToRequest(HttpRequest.Builder request, String[] headers, Duration timeout,
													   HttpClient.Version version) {
		if (headers != null) request = request.headers(headers);
		if (timeout != null) request = request.timeout(timeout);
		if (version != null) request = request.version(version);
		return request;
	}
	  HttpResponse<String> sendRequest(HttpRequest httpRequest) {
		httpClient = getHttpClient();
		try {
			return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
