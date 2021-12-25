package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OutputPictureTest {

	@Test
	public void testOutputPicture() throws Exception {
		int xLen=800;
		int yLen=600;
		OutputPicture output = new OutputPicture("test_output_picture.ppm", xLen,yLen);
		
		for (int yi=0;yi<yLen;yi++)
		{
			for (int xi=0;xi<xLen;xi++)
			{
				if ((yi<0.2*yLen) & (xi<0.2*xLen))
				{
				output.writePoint(ColorValue.LIGHTBLUE);
				}
				else if ((yi<0.2*yLen) & (xi>=0.2*xLen))
				{
					output.writePoint(ColorValue.RED);
				}
				else if ((yi>=0.2*yLen) & (xi<0.2*xLen))
				{
					output.writePoint(ColorValue.GREEN);
				}
				else
				{
					output.writePoint(ColorValue.GREY);
				}		
			}
		}
		output.close();
		assertEquals(1,1);
	}

	@Test
	public void testOutputCircle() throws Exception {
		int xLen=800;
		int yLen=600;
		OutputPicture output = new OutputPicture("test_output_circle.ppm", xLen,yLen);
		
		double x_2=(double)xLen/2.0;
		double y_2=(double)yLen/2.0;
		for (double yi=0;yi<yLen;yi++)
		{
			for (double xi=0;xi<xLen;xi++)
			{
				if ((Math.pow(yi-y_2,2)+Math.pow(xi-x_2,2))<0.5*Math.pow(x_2, 2))
				{
					output.writePoint(ColorValue.LIGHTBLUE);
				}
				else
				{
					output.writePoint(ColorValue.RED);	
				}
			}
		}
		output.close();
		assertEquals(1,1);
	}

}
