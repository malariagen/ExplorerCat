package net.explorercat.util.exceptions;

public class JsonException extends ExplorerCatRuntimeException {
	public JsonException(String msg) {
		super(msg);
	}

	public JsonException(String msg, Exception e) {
		super(msg + "\n\t" + e.getMessage(), e);
	}

	public JsonException(Exception e) {
		super(e);
	}
}
