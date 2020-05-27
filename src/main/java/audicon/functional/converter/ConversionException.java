package audicon.functional.converter;

public class ConversionException extends RuntimeException {

	public ConversionException(Throwable cause) {
		super("Failed to convert audio data", cause);
	}
}
