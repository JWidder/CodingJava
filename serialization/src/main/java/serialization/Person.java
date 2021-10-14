package serialization;

import java.io.Serializable;

public class Person implements Serializable {

		private static final long serialVersionUID = 1L;
		static String land = "Deuschland";
		private int alter;
		private String name;
		transient int height;
		// getters and setters
		public int getAlter() {
			return alter;
		}
		public void setAlter(int alter) {
			this.alter = alter;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
