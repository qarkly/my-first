package huawei;

import java.util.HashMap;
import java.util.Map;

public final class Demo
{
	/*
	 * 甲乙两个人用一个英语单词玩游戏。两个人轮流进行，每个人每次从中删掉任意一个字母，
	 * 如果剩余的字母序列是严格单调递增的（按字典序a < b < c <....<z)，
	 * 则这个人胜利。两个人都足够聪明（即如果有赢的方案，都不会选输的方案 ），甲先开始，问他能赢么？
	 * 输入： 一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单增的序列。 
	 * 输出：1表示甲可以赢，0表示甲不能赢。 
	 * 例如: 输入 bad， 则甲可以删掉b或者a,剩余的是ad或者bd，他就赢了，输出1。 
	 * 又如: 输入 aaa， 则甲只能删掉1个a，乙删掉一个a,剩余1个a，乙获胜，输出0。 
	 */
	public int who(String input)
	{
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    
	    
		
		return who_win(input, map); 

	}
	
	
	private int who_win(String input,Map<String, Integer> map)
	{
	    if(input.length() < 2)
	    {
	        return -1;
	    }
	    
	    if(input.length() == 2)
	    {
	        return 1;
	    }
	    
	    if(input.length() == 3){
	        if(input.charAt(0) >= input.charAt(1) && input.charAt(1) >= input.charAt(2)){
	            return 0;
	        }else {
                return 1;
            }
	    }
	    
	    for (int i = 0; i <= input.length()-1; i++)
        {
            if(i != 0 && input.charAt(i) == input.charAt(i-1)){
                continue;
            }
            
            String delString = input.substring(0,i)+input.substring(i+1);
            if(isSorted(delString)){
                return 1;
            }
        }
	    
	    for (int i = 0; i <= input.length()-1; i++)
        {
	        if(i != 0 && input.charAt(i) == input.charAt(i-1)){
                continue;
            }
	        
	        String delString = input.substring(0,i)+input.substring(i+1);
	        int state = 0;
	        if(map.containsKey(delString)){
	            state = map.get(delString);
	        }else {
                state = who_win(delString, map);
                map.put(delString, state);
            }
	        
	        if(state == 0){
	            return 1;
	        }
	        
        }
	    
	    map.put(input , 0);
	    return 0;
	}
	
	
	private boolean isSorted(String input)
	{
	    char[] chars = input.toCharArray();
	    for (int i = 0; i < chars.length-1; i++)
        {
            if(chars[i] >= chars[i+1]){
                return false;
            }
        }
	    return true;
	}
	
}  

	


