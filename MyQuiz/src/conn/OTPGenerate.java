package conn;
import java.util.Random;
import javax.swing.*;

public class OTPGenerate {
	public int OTP(){
		
		Random rand=new Random();
		int otp=rand.nextInt(99999);
		
		while(otp<12345)
			otp=rand.nextInt(99999);
		
		return otp;
	}
}
