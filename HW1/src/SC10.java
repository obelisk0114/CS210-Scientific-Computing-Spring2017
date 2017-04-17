
import java.math.BigDecimal;
import java.math.BigInteger;

public class SC10 {
	public static String solvequadratic(String as, String bs, String cs) {
		boolean fail2 = false;
		if (as.equals("0")) {
			System.out.print("Argument 'divisor' is 0. Need to use linear equation");
			float ans = -1 * ((new BigDecimal(cs)).divide(new BigDecimal(bs)).floatValue());
			System.out.print("\nx = " + ans);
			return ("x = " + ans);
		} else if (cs.equals("0")) {
			System.out.print("Formula 2 fail. Only use formula 1. ");
			fail2 = true;
		}
		int[] point = {0, 0, 0};
		boolean[] positive = {true, true, true};
		if (as.charAt(0) == '-')
			positive[0] = false;
		if (bs.charAt(0) == '-')
			positive[1] = false;
		if (cs.charAt(0) == '-')
			positive[2] = false;
		for (int i = as.length() - 1; i >= 0; i--) {
			if (as.charAt(i) == '.') {
				point[0] = i;
				break;
			}
		}
		for (int i = bs.length() - 1; i >= 0; i--) {
			if (bs.charAt(i) == '.') {
				point[1] = i;
				break;
			}
		}
		for (int i = cs.length() - 1; i >= 0; i--) {
			if (cs.charAt(i) == '.') {
				point[2] = i;
				break;
			}
		}
		float a = 0, b = 0, c = 0;
//		double a = 0, b = 0, c = 0;
		if ((as.length() - 1 - point[0]) >= 4 || (bs.length() - 1 - point[1]) >= 4 
				|| (cs.length() - 1 - point[2]) >= 4) {
			if (point[0] == 0 && point[1] == 0 && point[2] == 0) {
				BigInteger gcd = ((new BigInteger(as)).abs()).gcd((new BigInteger(bs)).abs());
				gcd = gcd.gcd((new BigInteger(cs)).abs());
				BigInteger bA = ((new BigInteger(as)).abs()).divide(gcd);
				BigInteger bB = ((new BigInteger(bs)).abs()).divide(gcd);
				BigInteger bC = ((new BigInteger(cs)).abs()).divide(gcd);
				if (bA.compareTo(new BigInteger("2896")) == 1 || 
						bB.compareTo(new BigInteger("2896")) == 1 ||
						bC.compareTo(new BigInteger("2896")) == 1) {
					BigInteger max = bA;
					if (bB.compareTo(max) == 1) {
						max = bB;
					}
					if (bC.compareTo(max) == 1) {
						max = bC;
					}
					int count = 0;
					while (max.compareTo(new BigInteger("2896")) == 1) {
						max = max.divide(new BigInteger("2"));
						count++;
					}
					BigDecimal bigA = new BigDecimal(bA);
					BigDecimal bigB = new BigDecimal(bB);
					BigDecimal bigC = new BigDecimal(bC);
					for (int i = 0; i < count; i++) {
						bigA = bigA.divide(new BigDecimal("2"));
						bigB = bigB.divide(new BigDecimal("2"));
						bigC = bigC.divide(new BigDecimal("2"));
//						System.out.println("i = " + i + " ; bigA = " + bigA + " ; bigB = " + bigB + " ; bigC = " + bigC);
					}
//					System.out.println("bigA = " + bigA + " ; bigB = " + bigB + " ; bigC = " + bigC); 
					a = bigA.floatValue();
					b = bigB.floatValue();
					c = bigC.floatValue();
//					a = bigA.doubleValue();
//					b = bigB.doubleValue();
//					c = bigC.doubleValue();
//					System.out.println("before ; a = " + a + " ; b = " + b + " ; c = " + c);
				}
				else {
					a = (float) bA.longValue();
					b = (float) bB.longValue();
					c = (float) bC.longValue();
				}
				if (!positive[0])
					a *= -1;
				if (!positive[1])
					b *= -1;
				if (!positive[2])
					c *= -1;
			}
			else {
				BigDecimal bigA = (new BigDecimal(as)).abs();
				BigDecimal bigB = (new BigDecimal(bs)).abs();
				BigDecimal bigC = (new BigDecimal(cs)).abs();
				BigDecimal max = bigA;
				if (bigB.compareTo(max) == 1) {
					max = bigB;
				}
				if (bigC.compareTo(max) == 1) {
					max = bigC;
				}
				int count = 0;
				while (max.compareTo(new BigDecimal("2896")) == 1) {
					max = max.divide(new BigDecimal("2"));
					count++;
				}
				for (int i = 0; i < count; i++) {
					bigA = bigA.divide(new BigDecimal("2"));
					bigB = bigB.divide(new BigDecimal("2"));
					bigC = bigC.divide(new BigDecimal("2"));
				}
				a = bigA.floatValue();
				b = bigB.floatValue();
				c = bigC.floatValue();
//				a = bigA.doubleValue();
//				b = bigB.doubleValue();
//				c = bigC.doubleValue();
				if (!positive[0])
					a *= -1;
				if (!positive[1])
					b *= -1;
				if (!positive[2])
					c *= -1;
			}
		}
		else {
			a = Float.parseFloat(as);
			b = Float.parseFloat(bs);
			c = Float.parseFloat(cs);
//			a = Double.parseDouble(as);
//			b = Double.parseDouble(bs);
//			c = Double.parseDouble(cs);
		}
//		System.out.println("after ; a = " + a + " ; b = " + b + " ; c = " + c);
		float part = b * b - 4 * a * c;
//		double part = b * b - 4 * a * c;
		String s = "x1 = ";
		if (part < 0) {
			part = (float) Math.sqrt(part * (-1));
			float real1 = (-b / (2 * a));
			float ima1 = (part / (2 * a));
			float real2 = ((-2 * c * b) / (2 * b * b - 4 * a * c));
			float ima2 = ((2 * c * part) / (2 * b * b - 4 * a * c));
//			double real1 = -b / (2 * a);
//			double ima1 = part / (2 * a);
//			double real2 = (-2 * c * b) / (2 * b * b - 4 * a * c);
//			double ima2 = (2 * c * part) / (2 * b * b - 4 * a * c);
			float diff = (float) (Math.abs(b) - part);
			if (Math.abs(a) <= 0.000001 || Math.abs(diff) <= 0.000001) {
				if (b > 0) {
					s = s + real2 + " + " + ima2 + " i" + ", x2 = " + real1 + " - " + ima1 + " i";
				}
				else {					
					s = s + real1 + " + " + ima1 + " i" + ", x2 = " + real2 + " - " + ima2 + " i";
				}
			}
			else {
				s = s + real1 + " + " + ima1 + " i" + ", x2 = " + real1 + " - " + ima1 + " i";
			}
			System.out.print("x11 = " + real1 + " + " + ima1 + " i" + ", x12 = " + real1 + " - " + ima1 + " i");
			if (!fail2) {
				System.out.print("; x21 = " + real2 + " + " + ima2 + " i" + ", x22 = " + real2 + " - " + ima2 + " i");
			}
		} else {
			part = (float) Math.sqrt(part);
			float x11 = ((-b + part) / (2 * a));
			float x12 = ((-b - part) / (2 * a));
			float x21 = (2 * c / (-b - part));
			float x22 = (2 * c / (-b + part));
//			double x11 = (-b + part) / (2 * a);
//			double x12 = (-b - part) / (2 * a);
//			double x21 = 2 * c / (-b - part);
//			double x22 = 2 * c / (-b + part);
			float diff = (float) (Math.abs(b) - part);
			if (Math.abs(a) <= 0.000001 || Math.abs(diff) <= 0.000001) {
				if (b > 0) {
					s = s + x21 + ", x2 = " + x12;
				}
				else {
					s = s + x11 + ", x2 = " + x22;
				}
			}
			else {
				s = s + x11 + ", x2 = " + x12;
			}
			System.out.print("x11 = " + x11 + ", x12 = " + x12);
			if (!fail2) {
				System.out.print("; x21 = " + x21 + ", x22 = " + x22);
			}
//			System.out.print("\ndiff = " + diff);
//			System.out.print("\npart = " + part);
		}
		return s;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n" + solvequadratic("6", "5", "-4"));
		String a = "6";
		String b = "5";
		String c = "-4";
		for (int i = 0; i < 154; i++) {
			a += "0";
			b += "0";
			c += "0";
		}
		System.out.println("\n" + solvequadratic(a, b, c));
		System.out.println("\n" + solvequadratic("0", "1", "1"));
		System.out.println("\n" + solvequadratic("1", "-100000", "1"));
		System.out.println("\n" + solvequadratic("1", "-4", "3.999999"));
		String a2 = "0.";
		String b2 = "-1";
		String c2 = "1";
		for (int i = 0; i < 154; i++) {
			a2 += "0";
		}
		a2 += "1";
		for (int i = 0; i < 155; i++) {
			b2 += "0";
			c2 += "0";
		}
		System.out.println("\n" + solvequadratic(a2, b2, c2));
	}

}
