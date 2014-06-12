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
		int iterator;
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
				iterator = index-2;
				while(temp.charAt(iterator) == '~' && iterator >= 0)
				{
					left = left.insert(0,'~');
					iterator--;
				}
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
				iterator = index + 4;
				while(temp.charAt(iterator) == '~')
				{
					right = right.insert(0,'~');
					iterator++;
				}
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
		/* variable */
		int index;
		int iterator = 0;
		int counter;
		StringBuffer temp = new StringBuffer(output);
		
		/* Program */
		/* Menghapus semua ~~ */
		index = temp.indexOf("~~");
		while(index >= 0)
		{
			temp.replace(index, index+2, "");
			index = temp.indexOf("~~");
		}
		
		/* Menangani kasus (...) */
		index = temp.lastIndexOf("~(");
		while(index >= 0)
		{
			iterator = index + 2;
			counter = 1;
			while(counter > 0)
			{
				if(temp.charAt(iterator) == '(')
				{
					counter ++;
					iterator ++;
				}
				else if(temp.charAt(iterator) == ')')
				{
					counter --;
					iterator ++;
				}
				else if(temp.charAt(iterator) == '~')
				{
					temp.deleteCharAt(iterator);
					iterator++;
				}
				else if(temp.charAt(iterator) == 'V')
				{
					temp.replace(iterator, iterator + 1, "^");
					iterator++;
				}
				else if(temp.charAt(iterator) == '^')
				{
					temp.replace(iterator, iterator+1, "V");
					iterator++;
				}
				else 
				{
					temp.insert(iterator, '~');
					iterator ++;
					iterator ++;
				}
			}
			temp.deleteCharAt(index);
			index = temp.lastIndexOf("~(");
		}
		output = temp.toString();
		System.out.println("Output from N : " + output);
	}
	public void D()
	{
		/* Bentuk : 
		 * aV(b^c)
		 * (aVb)^c
		 */
		
		/* variable */
		int iterator;
		int i;
		int index;
		int counter;
		char operator;
		StringBuffer temp = new StringBuffer(input);
		StringBuffer leftOperand = new StringBuffer();
		StringBuffer rightOperand = new StringBuffer();
		
		/* program */
		System.out.println("input : " + input);
		iterator = 0;
		while(iterator < temp.length())
		{
			leftOperand = new StringBuffer();
			rightOperand = new StringBuffer();
			if(temp.charAt(iterator) == 'V' || temp.charAt(iterator) == '^')
			{
				operator = temp.charAt(iterator);
				
				/* cari operand sebelah kirinya */
				i = iterator - 1;
				if(temp.charAt(i) != ')')
				{
					leftOperand.insert(0, temp.charAt(i));
					i--;
					if(i >= 0 && temp.charAt(i) == '~')
					{
						leftOperand.insert(0,'~');
					}
				}
				else
				{
					leftOperand.insert(0,')');
					counter = 1;
					i--;
					while(i >= 0 && counter > 0)
					{
						if(temp.charAt(i) == '(')
						{
							counter --;
						}
						else if(temp.charAt(i) == ')')
						{
							counter ++;
						}
						leftOperand.insert(0,temp.charAt(i));
						i--;
					}
				}
				
				/* cari operand sebelah kanan */
				i = iterator + 1;
				if(temp.charAt(i) != '(')
				{
					if(temp.charAt(i) == '~')
					{
						rightOperand.insert(0,'~');
						i++;
					}
					rightOperand.insert(rightOperand.length(),temp.charAt(i));
				}
				else
				{
					rightOperand.insert(0,'(');
					counter = 1;
					i++;
					while(i >= 0 && counter > 0)
					{
						if(temp.charAt(i) == '(')
						{
							counter ++;
						}
						else if(temp.charAt(i) == ')')
						{
							counter --;
						}
						rightOperand.insert(rightOperand.length(),temp.charAt(i));
						i++;
					}
				}
				if((contains(leftOperand, "(") && !contains(rightOperand,"(")) || 
						(contains(rightOperand, "(") && !contains(leftOperand,"(")))
				{
					System.out.println("Operator : " + operator);
					System.out.println("LeftOperand = " + leftOperand.toString());
					System.out.println("RightOperand = " + rightOperand.toString());
				}
			}
			iterator ++;
		}
	}
	
	private void O()
	{
		System.out.println("Method O");
	}
	public void INDO()
	{
		input = input.replaceAll(" ", "");
		I();
		N();
		D();
		O();
	}
	private boolean contains(StringBuffer input, String c)
	{
		if(input.indexOf(c) >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void main(String[] args) 
	{
		ClausalForm CF = new ClausalForm("aV(b^c^d)");
		CF.D();
	}

}
