package Time;

public class Time {
	public static String time(long x) {
		String s = null;
		
		if(x<60) {
			s = x+"ñ.";
		} else
		if(x<3600) {
			s = Math.round(x/60)+"ì. "+(x-Math.round(x/60)*60)+"ñ.";
		} else 
	    if(x<86400) {
	    	s = Math.round(x/3600)+"÷. "+Math.round((x-Math.round(x/3600)*3600)/60)+"ì. "+(x-Math.round(x/60)*60)+"ñ. ";
	    } else {
	    	s = Math.round(x/86400)+"ä. "+(x-Math.round(x/86400)*86400)/3600+"÷. "+Math.round((x-Math.round(x/3600)*3600)/60)+"ì. "+(x-Math.round(x/60)*60)+"ñ. ";
	    }
		return s;
	}
}
