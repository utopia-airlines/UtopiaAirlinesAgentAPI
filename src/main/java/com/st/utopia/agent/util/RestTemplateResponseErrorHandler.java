package com.st.utopia.agent.util;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	/* By default it would return true and throw a error if it was anything other
	 * than a 200. Now, it will say that anything (including 200-500) is fine, and
	 * will not throw an error. */
	@Override
	public boolean hasError(ClientHttpResponse httpResponse) {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#handleError(org.springframework.http.client.ClientHttpResponse)
	 *
	 * Do not need to handle http error codes, just pass it through
	 */
	@Override
	public void handleError(ClientHttpResponse httpResponse) {}
}
