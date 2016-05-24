import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.lang.StringBuilder;

import javax.imageio.ImageIO;

public class testingMain {
	public static void main(String args[]){
		imageDownloader downloader = new imageDownloader();
		String imageURL;
		String targetURL;
		BufferedImage fullImage = new BufferedImage(500,600,BufferedImage.TYPE_INT_RGB);
		Graphics g = fullImage.getGraphics();
		//downloading image
		for(int y=0;y<=6;y++){
			for(int x=0;x<=4;x++){
				StringBuilder userURL = new StringBuilder("http://images1.flashphotography.com/Magnifier/MagnifyRender.ashx?X=");
				StringBuilder fileURL = new StringBuilder("./temp/image");
				int xAxis = 50 + 100*x;
				int yAxis = 50 + 100*y;
				userURL.append(Integer.toString(xAxis)).append("&Y=").append(Integer.toString(yAxis)).append("&O=26891370&R=00010&F=0171&A=71714&rand=0.50");
				imageURL = userURL.toString();
				downloader.setImageURL(imageURL);
				fileURL.append(Integer.toString(x)).append(Integer.toString(y)).append(".jpeg");
				targetURL = fileURL.toString();
				downloader.setTargetURL(targetURL);
				downloader.downloadImage();
				//cropping all images
				ImageCropper.cropImage(44, 44, 100, 100, targetURL);
				//draw the cropped image into one buffered image
				try{
					BufferedImage tempBI = ImageIO.read(new File(targetURL));
					g.drawImage(tempBI,x*100,y*100,null);
				}
				catch(IOException e){
					System.out.println(e.getMessage());
				}
			}
		}
		try{
			ImageIO.write(fullImage,"jpeg",new File("./output.jpeg"));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

	}
}
