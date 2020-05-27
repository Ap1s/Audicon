package audicon.functional.converter;

import static audicon.functional.converter.Converter.convertFrom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class Converter {

	private InputStream input;
	private AudioFormat audioFormat;
	private boolean close;
	
	public Converter() {

	}

	public Converter(InputStream input) {
		this(input, false);
	}

	public Converter(InputStream input, boolean close) {
		this.input = input;
		this.close = close;
	}

	public static Converter convertFrom(InputStream input) {
		return new Converter(input, false);
	}

	public static Converter convertFrom(byte[] mp3Content) {
		return new Converter(new ByteArrayInputStream(mp3Content), true);
	}

	public void convertToMp3() throws IOException, UnsupportedAudioFileException {
		try (
				final InputStream inputStream = getClass().getResourceAsStream("/test.wav");
				final ByteArrayOutputStream output = new ByteArrayOutputStream();
		) {
			final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, false, false);

			convertFrom(inputStream).withTargetFormat(audioFormat).to(output);

			final byte[] wavContent = output.toByteArray();

			final AudioFileFormat actualFileFormat = AudioSystem
					.getAudioFileFormat(new ByteArrayInputStream(wavContent));

			Files.write(Paths.get("C://Users/" + System.getenv("USERNAME") + "/Desktop/result.mp3"), wavContent);
		} finally {
			// intentionally left blank
		}
	}
	
	public Converter withTargetFormat(AudioFormat targetAudioFormat) {
		this.audioFormat = targetAudioFormat;
		return this;
	}

	public void to(OutputStream output) {
		try (
				final ByteArrayOutputStream rawOutputStream = new ByteArrayOutputStream()
		) {
			convert(input, rawOutputStream, getTargetFormat());

			final byte[] rawResult = rawOutputStream.toByteArray();
			final AudioInputStream audioInputStream = new AudioInputStream(new ByteArrayInputStream(rawResult),
					getTargetFormat(), rawResult.length);
			AudioSystem.write(audioInputStream, Type.WAVE, output);
		} catch (Exception e) {
			throw new ConversionException(e);
		} finally {
			closeInput();
		}
	}

	public byte[] toByteArray() {
		try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			to(output);
			return output.toByteArray();
		}  catch (IOException e) {
			throw new ConversionException(e);
		}
	}

	private void closeInput() {
		if (this.close) {
			try {
				input.close();
			} catch (IOException e) {
				// Sad but true;
			}
		}
	}

	private void convert(InputStream input, OutputStream output, AudioFormat targetFormat) throws Exception {

		try (
				final AudioInputStream rawSourceStream = AudioSystem.getAudioInputStream(input)
		) {
			final AudioFormat sourceFormat = rawSourceStream.getFormat();
			final AudioFormat convertFormat = getAudioFormat(sourceFormat);

			try (
					final AudioInputStream sourceStream = AudioSystem
							.getAudioInputStream(convertFormat, rawSourceStream);
					final AudioInputStream convertStream = AudioSystem.getAudioInputStream(targetFormat, sourceStream);
			) {
				int read;
				final byte[] buffer = new byte[8192];
				while ((read = convertStream.read(buffer, 0, buffer.length)) >= 0) {
					output.write(buffer, 0, read);
				}
			}
		}
	}

	private AudioFormat getTargetFormat() {
		return this.audioFormat == null
				? new AudioFormat(44100, 8, 1, true, false)
				: audioFormat;
	}

	private AudioFormat getAudioFormat(AudioFormat sourceFormat) {
		return new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				sourceFormat.getSampleRate(),
				16,
				sourceFormat.getChannels(),

				sourceFormat.getChannels() * 2,
				sourceFormat.getSampleRate(),
				false);
	}
}
