package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Johannes Widder
 *
 */
public class OutputPicture {
	BufferedWriter writer;
	
	/**
	 * @param fileName
	 * @param xLen
	 * @param yLen
	 */
	@SuppressWarnings("nls")
	public OutputPicture(String fileName,int xLen,int yLen)
	{
		try {
			this.writer = new BufferedWriter(new FileWriter(fileName));
			this.writer.write("P3 \n");
			this.writer.write(String.format("%d %d\n",xLen,yLen));
			this.writer.write("255\n");
			this.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param inColor
	 */
	@SuppressWarnings("nls")
	public void writePoint(Color inColor)
	{
		try {
			this.writer.write(String.format("%d %d %d\n",inColor.getColorValue(0),inColor.getColorValue(1),inColor.getColorValue(2)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param inColor
	 */
	@SuppressWarnings("nls")
	public void writePoint(ColorValue inColor)
	{
		try {
			this.writer.write(String.format("%d %d %d\n",(int)inColor.getRedValue(),(int)inColor.getGreenValue(),(int)inColor.getBlueValue())); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void close()
	{
	    try {
			this.writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
