package audicon.functional.extractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import audicon.functional.bo.Track;

public class MetaDataExtractor {
	
	public static Track getTrackMetaDataFromByteArray(final File sourceFile) {
		try {
			byte[] arr = new byte[127];
			RandomAccessFile accessFile = new RandomAccessFile(sourceFile, "r");
			// Seek to the end of file
			accessFile.seek(sourceFile.length() - 127);
			// Read it out.
			accessFile.read(arr, 0, 127);
			
			String title = getMetaData(arr, 3, 32);
			String artist = getMetaData(arr, 33, 62);
			String length = "";
			
			return new Track(title, artist, length, null);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static String getMetaData(byte[] arr, int from, int to) {
		StringBuilder sb = new StringBuilder();
		for(int i = from; i < to; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}
}
