package huawei;

public class Point {
	
	public Point parentPoint;//���ڵ�
	
	public int x;//x��
	
	public int y; //y��
	
	public int F;
	
	public int G;
	
	public int H;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void calcF()
	{
		this.F = this.H + this.G;
	}

	@Override
	public String toString() {
		return "<"+this.x+","+this.y+">";
	}

	
}
