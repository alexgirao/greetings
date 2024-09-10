// https://spring.io/guides/gs/rest-service/

package com.acme.labs.common;

import java.util.concurrent.TimeUnit;

public class Greeting {
	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public Greeting(long id, String content, int blockForMillis) {
		this(id, content);
		try {
			TimeUnit.MILLISECONDS.sleep(blockForMillis);
		} catch (Throwable e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e.getMessage());
		}
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
