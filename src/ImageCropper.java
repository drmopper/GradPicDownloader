/* ImageCropper.java */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageCropper {
	public ImageCropper(){
		
	}
	public static void cropImage(int x, int y, int xlength, int ylength,String filePath){
		try {
			BufferedImage originalImgage = ImageIO.read(new File(filePath));
			BufferedImage SubImgage = originalImgage.getSubimage(x,y, xlength, ylength);
			File outputfile = new File(filePath);
			ImageIO.write(SubImgage, "jpeg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}