package huawei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Demo {
	
	
	
	
	/*
	  * 每组测试数据包括三个整数t,n,m(00)，分别代表公主能坚持的天数,迷宫的长和高。紧接着有m行，n列字符，由"."，"*"，"P"，"S"组成。
	  * 其中 "." 代表能够行走的空地。 "*" 代表墙壁，Jesse不能从此通过。
	  * "P" 是公主所在的位置。 "S" 是Jesse的起始位置。 每个时间段里Jesse
	  * 只能选择“上、下、左、右”任意一方向走一步。
	  * 迷宫布局（这里用二维数组实现布局） m 迷宫（数组）行数 n 迷宫（数组）列数 
	  * T 公主能坚持的天数
	  * Return Value 0 可以救出公主 -1 不可以救出公主
	  */
	 public int SSavep(char[][] visited, int t, int n, int m) {
	  // 这里面添加函数功能
		 this.m = m;
		 this.n = n;
		 Point hero = null;
		 Point princess = null;
		 
		 for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if(visited[i][j] == 'S')
				{
					hero = new Point(i, j);
				}
				
				if(visited[i][j] == 'P')
				{
					princess = new Point(i, j);
				}
			}
		}
//		Print(hero);
//		Print(princess);
		openList.add(hero);
		
		while (!openList.isEmpty()) {
			Point tmpStart = findMiniPoint();
//			Print(tmpStart);
			openList.remove(tmpStart);
			closeList.add(tmpStart);
			
			for (Point point : findSurrounds(tmpStart,visited)) {
				if(find(openList, point.x, point.y) != null){
					int G = calcG(tmpStart)+1;
					if(G < point.G){
						point.parentPoint = tmpStart;
						point.G = G;
						point.calcF();
					}
				}else {
					point.parentPoint = tmpStart;
					point.G = calcG(point);
					point.H = calcH(point,princess);
					point.calcF();
					openList.add(point);
				}
			}
			
			if(find(openList, princess.x, princess.y) != null){
				break;
			}
		}
		
		if(find(openList, princess.x, princess.y) != null)
		{
			StringBuilder sb  = new StringBuilder();
			
			Point tmp = find(openList, princess.x, princess.y);
			
			int step = 0;
			
			while (tmp != null) {
				step++;
				sb.append('(').append(tmp.x).append(',').append(tmp.y).append(')').append("-->");
				tmp = tmp.parentPoint;
			}
			System.out.println(sb.toString());
			System.out.println("需要的step："+(step-1));
			
			if(step-1 > t)
			{
				return 0;
			}
			return step-1;
		}
		System.out.println("not found");
		 return 0;
	  
	 }
	 
	 private void Print(Point point)
	 {
		 System.out.println("<"+point.x+","+point.y+">");
	 }
	 
	 public static void main(String[] args) {
		char[][] chars = {{'.','.','.','.','.'},
				           {'.','*','*','*','.'},
				           {'.','.','.','.','.'},
				           {'S','.','*','P','.'}};
		
		new Demo().SSavep(chars, 4, 5, 4);
	}
	 
	 private int calcH(Point point, Point princess) {
		int h = Math.abs(princess.x-point.x) + Math.abs(princess.y - point.y);
		return h;
	}



	private int calcG( Point point) {
		
		return point.parentPoint == null ? 1 : point.parentPoint.G+1;
	}



	private List<Point> findSurrounds(Point tmpStart,char[][] visited) {
		List<Point> list = new ArrayList<Point>(4);
		
		int orgX = tmpStart.x;
		
		int orgY = tmpStart.y;
		
		if(orgX-1 >= 0)
		{
			if(visited[orgX-1][orgY] != '*' && find(closeList, orgX-1, orgY) == null){
				Point point = find(openList, orgX-1, orgX);
				if( point != null)
				{
					list.add(point);
				}else {
					list.add(new Point(orgX-1, orgY));
				}
			}
		}
		
		if(orgX+1 <= m-1)
		{
			if(visited[orgX+1][orgY] != '*' && find(closeList, orgX+1, orgY) == null){
				Point point = find(openList, orgX+1, orgX);
				if( point != null)
				{
					list.add(point);
				}else {
					list.add(new Point(orgX+1, orgY));
				}
			}
		}
		
		if(orgY-1 >= 0)
		{
			if(visited[orgX][orgY -1] != '*' && find(closeList, orgX, orgY-1) == null){
				Point point = find(openList, orgX, orgX-1);
				if( point != null)
				{
					list.add(point);
				}else {
					list.add(new Point(orgX, orgY-1));
				}
			}
		}
		
		if(orgY+1 <= n-1)
		{
			if(visited[orgX][orgY + 1] != '*' && find(closeList, orgX, orgY+1) == null){
				Point point = find(openList, orgX, orgX+1);
				if( point != null)
				{
					list.add(point);
				}else {
					list.add(new Point(orgX, orgY+1));
				}
			}
		}
		return list;
	}


	 private Point find(List<Point> list ,int x,int y)
	 {
		 for (Point point : list) {
			if(point.x == x && point.y == y)
			{
				return point;
			}
		}
		 return null;
	 }

	private Point findMiniPoint() {
		 Point mini = openList.get(0);
		 
		 for (Point point : openList) {
			if(point.F <= mini.F)
			{
				mini = point;
			}
		}
		return mini;
	}

	private int m,n;

	private List<Point> openList = new LinkedList<Point>();
	 
	 private List<Point> closeList = new LinkedList<Point>();

}
