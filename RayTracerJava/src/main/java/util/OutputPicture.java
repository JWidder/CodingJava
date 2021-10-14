package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputPicture {
	BufferedWriter writer;
	
	public OutputPicture(String fileName,int xLen,int yLen)
	{
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("P3 \n");
			writer.write(String.format("%d %d\n",xLen,yLen));
			writer.write("255\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writePoint(Color inColor)
	{
		try {
			writer.write(String.format("%d %d %d\n",(int)inColor.getRedValue(),(int)inColor.getGreenValue(),(int)inColor.getBlueValue()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writePoint(ColorValue inColor)
	{
		try {
			writer.write(String.format("%d %d %d\n",inColor.getRedValue(),inColor.getGreenValue(),inColor.getBlueValue()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close()
	{
	    try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
