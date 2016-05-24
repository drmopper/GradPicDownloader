import java.awt.Image;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

public class imageDownloader {
	
	public imageDownloader(String InputURL,String OutputURL){
		imageURL = InputURL;
		targetURL = OutputURL;
	}
	public imageDownloader(){
		
	}
	
	private String imageURL;

	private String targetURL;
	
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	
	public void downloadImage(){
		try{
			URL imageDownloadURL = new URL(imageURL);
			InputStream in = new BufferedInputStream(imageDownloadURL.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf))){
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			File outputFile = new File(targetURL);
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(response);
			fos.close();
			System.out.println("Finished");
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
