package stringHandling;

/**
 * @author Johannes
 *
 */

public enum ColorValue {
	GREY(142, 142, 147),
	RED(255, 59, 48),
	GREEN(76, 217, 100),
	PURPLE(88, 86, 214),
	LIGHTBLUE (52, 170, 220), 
	BLACK ( 0 , 0 , 0);    

	private ColorValue(final Integer red, final Integer green, final Integer blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	private final Integer red, green, blue;

//	public String getRGB() {
//		return red + "," + green + "," + blue;
//	}
	
    public int getRedValue() {
        return red;
    }

    public int getBlueValue() {
        return blue;
    }

    public int getGreenValue() {
        return green;
    }

//    @Override
//    public String toString() {
//        return red + "," + green + "," + blue;
//    }
}

//public class  helloWorld{
//public static void main(String[] args) {
//
//    String test = Colors.LIGHTBLUE.getRGB();
//    System.out.println(test);
//
//}
//}

//	Schwarz	#000000	0	0	0
//	Grau	#888888	136	136	136
//	Weiss	#FFFFFF	255	255	255
//	Rot	#FF0000	255	0	0
//	Dunkelrot	#880000	136	0	0	#77FFFF
//	Gruen	#00FF00	0	255	0
//	Dunkelgruen	#008800	0	136	0
//	Blau	#0000FF	0	0	255
//	Dunkelblau	#000088	0	0	136
//	Cyan	#00FFFF	0	255	255
//	Dunkelcyan	#008888	0	136	136
//	Magenta	#FF00FF	255	0	255
//	Dunkelmagenta	#880088	136	0	136
//	Gelb	#FFFF00	255	255	0
//	Dunkelgelb	#888800	136	136	0
//	Blaugruen	#00FF88	0	255	136	
//	Himmelblau	#0088FF	0	136	255
//	
//	
//	Black	#000000	(0,0,0)
// 	White	#FFFFFF	(255,255,255)
// 	Red	#FF0000	(255,0,0)
// 	Lime	#00FF00	(0,255,0)
// 	Blue	#0000FF	(0,0,255)
// 	Yellow	#FFFF00	(255,255,0)
// 	Cyan / Aqua	#00FFFF	(0,255,255)
// 	Magenta / Fuchsia	#FF00FF	(255,0,255)
// 	Silver	#C0C0C0	(192,192,192)
// 	Gray	#808080	(128,128,128)
// 	Maroon	#800000	(128,0,0)
// 	Olive	#808000	(128,128,0)
// 	Green	#008000	(0,128,0)
// 	Purple	#800080	(128,0,128)
// 	Teal	#008080	(0,128,128)
// 	Navy	#000080	(0,0,128)
	
//}
