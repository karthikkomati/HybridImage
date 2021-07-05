package uk.ac.soton.ecs.kk8g18.hybridimages;

import java.io.File;
import java.io.IOException;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.convolution.FConvolution;
import org.openimaj.image.processing.convolution.FGaussianConvolve;
import org.openimaj.image.processing.resize.BilinearInterpolation;
import org.openimaj.image.processing.resize.ResizeProcessor;
import org.openimaj.image.typography.hershey.HersheyFont;

/**
 * OpenIMAJ Hello world!
 *
 */
public class App extends FConvolution{
	
    public App(float[][] kernel) {
		super(kernel);
		// TODO Auto-generated constructor stub
	}

	public static void main( String[] args ) throws IOException {
    	//Create an image
        //MBFImage image = new MBFImage(320,70, ColourSpace.RGB);

        //testConvolution();
		testHybrid();
	}
	
	public static void testConvolution() throws IOException {
		float [][] k= {
        		{0,-1,2,},
        		{-1,5,-1},
        		{0,-1,0},
        		

			};
        
        App a = new App(k);
        FImage image = ImageUtilities.readF(new File("C:\\Users\\karth\\Desktop\\ComputerVision\\hybrid-images\\data\\dog.bmp"));
        
        //DisplayUtilities.display(image);
        FImage image2 = image.clone();
        
        //Display the image
        
        a.processImage(image);
        
        DisplayUtilities.display(image,"required");
         
        MyConvolution mc = new MyConvolution(k);
        mc.processImage(image2);
        FImage image3 = image2.subtract(image);
        //DisplayUtilities.display(image3,"sub");

        //image2.subtract(image3);
        System.out.println(image2.equalsThresh(image,0));
        
        DisplayUtilities.display(image2,"image2");
        System.out.println(image.height+":"+image.width);
        System.out.println(image2.height+":"+image2.width);
        if (image.height != image2.height || image.width != image2.width) {
        	                       System.out.println("height or width");
        }
        
        int ab = 0;
        	                for (int i = 0; i < image.height; i++) {
        	                        for (int j = 0; j < image.width; j++) {
        	                                if (Math.abs(image2.pixels[i][j] - image.pixels[i][j]) != 0) {
        	                                	ab++;
        	                                        //System.out.print(i + " and "+ j + " Diff:" +(image2.pixels[i][j] - image.pixels[i][j])+ "    ");
        	                                }
        	                        }
        	                }
        
    
        	                System.out.println("wrong:" + ab+ " end");
		
	}
	
	public static void testHybrid() throws IOException {
		
		MBFImage image = ImageUtilities.readMBF(new File("C:\\Users\\karth\\Desktop\\ComputerVision\\hybrid-images\\data\\test11.png"));
		MBFImage image2 = ImageUtilities.readMBF(new File("C:\\Users\\karth\\Desktop\\ComputerVision\\hybrid-images\\data\\test13.png"));
		
		MBFImage im = MyHybridImages.makeHybrid(image, (float) 5, image2, 5);
		ResizeProcessor re = new ResizeProcessor(2);
		//
		//MBFImage im2 = re.halfSize(im);
		//im.drawImage(im2, 0, 500);
		DisplayUtilities.display(im,"final");
		
	}
}
