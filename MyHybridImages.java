package uk.ac.soton.ecs.kk8g18.hybridimages;

import java.io.File;
import java.io.IOException;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.convolution.Gaussian2D;

public class MyHybridImages {
	
	public static MBFImage makeHybrid(MBFImage lowImage, float lowSigma, MBFImage highImage, float highSigma) throws IOException {
		MBFImage hybrid = null;
		
				
		
		//calculating the low size from the given low sigma
		int lowSize = (int) (8.0f * lowSigma + 1.0f);     
		if(lowSize%2 == 0)
			lowSize++;
		
		
		//convoluting the image to create a low pass image
		MyConvolution m = new MyConvolution(Gaussian2D.createKernelImage(lowSize, lowSigma).pixels);  
		MBFImage im = lowImage.process(m);
		
		
		
		//calculating the high size from the given high sigma
		int highSize = (int) (8.0f * highSigma + 1.0f);
		if(highSize%2 == 0)
			highSize++;
		
		//convoluting the high image
		MyConvolution m2 = new MyConvolution(Gaussian2D.createKernelImage(highSize, highSigma).pixels);
		MBFImage im2 = highImage.process(m2);
		
		//subtracting the convoluted image form the original image to create a high pass image
		MBFImage im3 = highImage.subtract(im2);
		
		
		

		
		//creating the hybrid image by adding both images
		hybrid = im.add(im3);
		//DisplayUtilities.display(hybrid,"hybrid");
		
		
		return hybrid;
	}
}