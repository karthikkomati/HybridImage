package uk.ac.soton.ecs.kk8g18.hybridimages;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
	private float[][] kernel;


	public MyConvolution(float[][] kernel) {

		this.kernel = kernel;	
		
		
	}	
	
	
	public void processImage(FImage image) {
		//gets the height and width of kernel
		int kh = kernel.length;
		int kw = kernel[0].length;

		//makes a deep copy of the image so the previously changed pixels wont effect it
		final FImage image2 = image.clone();
		
			
			//loops through the entire image
		  	for (int i = 0; i < image.height; i++) {
		  		for (int j = 0; j < image.width; j++) {
		  			
		  			
		  			float sum = 0;
		  			
		  			//loops through the kernel
		  			for (int k = 0, k1 = kh - 1; k < kh; k++, k1--) {
		  				for (int l = 0, l1 = kw - 1; l < kw; l++, l1--) {
		  					
		  					//identifies which pixel value need to be multiplied and added to the sum
		  					int a = i + k - (kh/2);
		  					int b = j + l - (kw/2);
		  					
		  					//zero-padding, if the value goes outside the image adds zero to the sum
		  					if(a>=image.height||b>=image.width||a<0||b<0) {
		  						sum +=0;
		  					}
		  					
		  					//calculates the value of a pixel multiplied by the kernel value corresponding to it and adds it to the sum
		  					else {
		  						sum += image2.pixels[a][b] * kernel[k1][l1];
		  					}

		  				}
		  			}
		  			
		  			//sets the pixel as the total sum value
		  			image.pixels[i][j] = sum;
		  		}
		  	}
		  	
		  	

		  	
		  	//DisplayUtilities.display(image,"convoluted image");
	}
}