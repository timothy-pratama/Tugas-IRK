package com.example.indo;

public class ClausalForm 
{
	/* Attribute */
	private String input;
	private String output;
	
	/* Constructor */
	public ClausalForm()
	{
		input = "";
		output = "";
	}
	public ClausalForm(String input)
	{
		this.input = input;
		output = "";
	}
	
	/* Setter, Getter */
	public void setInput(String input)
	{
		this.input = input;
	}
	public String getInput()
	{
		return input;
	}
	public String getOutput()
	{
		return output;
	}
	private void clearOutput()
	{
		output = "";
	}
	private void clearInput()
	{
		input = "";
	}
	
	/* Method for printing */
	public String toString()
	{
		StringBuffer temp = new StringBuffer();
		temp.append("Input : ");
		temp.append(input);
		temp.append("\n");
		temp.append("Output : ");
		temp.append(output);
		temp.append("\n");
		return temp.toString();
	}
	public void Print()
	{
		System.out.println(this.toString());
	}
	private void I()
	{
		/* Variable */
		int i,j;
		int index;
		int location;
		int counter;
		StringBuffer left;
		StringBuffer right;
		StringBuffer finalleft;
		StringBuffer finalright;
		StringBuffer result;
				
		/* Program */
		StringBuffer temp = new StringBuffer(input);
		
		/* Handle Case where <=> exist */
		index = temp.indexOf("<=>");
		while(index >= 0)
		{
			left = new StringBuffer();
			right = new StringBuffer();
			finalleft = new StringBuffer();
			finalright = new StringBuffer();
			result = new StringBuffer();
			/* Find the left Operand */
			if(temp.charAt(index - 1) != ')')
			{
				left = left.insert(0, temp.charAt(index-1));
			}
			else
			{
				left.insert(0, ')');
				counter = 1;
				j = index - 2;
				while(counter > 0)
				{
					left = left.insert(0, temp.charAt(j));
					if(temp.charAt(j)=='(')
					{
						counter --;
					}
					else if(temp.charAt(j)==')')
					{
						counter ++;
					}
					if (counter > 0)
					{
						j--;
					}
				}
			}
			
			/* Find the right operand */
			if(temp.charAt(index + 3) != '(')
			{
				right = right.insert(0, temp.charAt(index+3));
			}
			else
			{
				right.insert(0, '(');
				counter = 1;
				j = index + 4;
				while(counter > 0)
				{
					right = right.insert(right.length(), temp.charAt(j));
					if(temp.charAt(j)=='(')
					{
						counter ++;
					}
					else if(temp.charAt(j)==')')
					{
						counter --;
					}
					if (counter > 0)
					{
						j++;
					}
				}
			}
			
			/* proses sebelah kiri */
			finalleft.append("((");
			finalleft.append(left);
			finalleft.append("=>");
			finalleft.append(right);
			finalleft.append(")");
				
			/* proses sebelah kanan */
			finalright.append("(");
			finalright.append(right);
			finalright.append("=>");
			finalright.append(left);
			finalright.append("))");
			
			/* proses result */
			result.append(finalleft);
			result.append("^");
			result.append(finalright);
			
			/* proses temp */
			temp.replace(index - left.length(), index + 3 + right.length(), result.toString());
			index = temp.indexOf("<=>");
		}
		
		/* Handle case with implication only */
		index = temp.indexOf("=>");
		while(index > 0)
		{
			if(temp.charAt(index - 1) != ')')
			{
				location = index - 1;
			}
			else
			{
				counter = 1;
				j = index - 2;
				while(counter > 0)
				{
					if(temp.charAt(j) == '(')
					{
						counter --;
					}
					else if (temp.charAt(j)==')')
					{
						counter ++;
					}
					if(counter > 0)
					{
						j--;
					}
				}
				location = j;
			}
			temp = temp.insert(location, '~');
			temp = temp.replace(index+1, index+3, "V");
			index = temp.indexOf("=>");
		}
		output = temp.toString();
		System.out.println("Output from I : " + output);
	}
	private void N()
	{
		System.out.println("Method N");
	}
	private void D()
	{
		System.out.println("Method D");
	}
	private void O()
	{
		System.out.println("Method O");
	}
	public void INDO()
	{
		I();
		N();
		D();
		O();
	}
	
	public static void main(String[] args) 
	{
		ClausalForm CF = new ClausalForm("(a=>b)V(b=>c)");
		CF.INDO();
	}

}
