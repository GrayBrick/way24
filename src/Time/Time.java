package Time;

public class Time {
	public static String time(long x) {
		String s = null;
		
		if(x<60) {
			s = x+"�.";
		} else
		if(x<3600) {
			s = Math.round(x/60)+"�. "+(x-Math.round(x/60)*60)+"�.";
		} else 
	    if(x<86400) {
	    	s = Math.round(x/3600)+"�. "+Math.round((x-Math.round(x/3600)*3600)/60)+"�. "+(x-Math.round(x/60)*60)+"�. ";
	    } else {
	    	s = Math.round(x/86400)+"�. "+(x-Math.round(x/86400)*86400)/3600+"�. "+Math.round((x-Math.round(x/3600)*3600)/60)+"�. "+(x-Math.round(x/60)*60)+"�. ";
	    }
		return s;
	}
}
