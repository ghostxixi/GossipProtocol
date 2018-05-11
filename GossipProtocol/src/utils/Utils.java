package utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

//sql������'��Ϊ$
public class Utils {
	public static String CheckString(String str)
    {
        String returnStr = "";
        if(str.indexOf("'") != -1) //�ж��ַ����Ƿ��е�����
        {
            returnStr = str.replace("'", "$");
            str = returnStr;
        }
        else if(str.indexOf("$") != -1){
        	returnStr = str.replace("$", "'");
            str = returnStr;
        }
        return str;
    }
	
	public static InetSocketAddress parseInetSocketAddress(String str)
			throws UnknownHostException {
		String[] strs = str.split(":");
		if (strs.length == 2) {
			return new InetSocketAddress(InetAddress.getByName(strs[0]),
					Integer.parseInt(strs[1]));
		} else {
			throw new UnknownHostException(str);
		}
	}
	

}
