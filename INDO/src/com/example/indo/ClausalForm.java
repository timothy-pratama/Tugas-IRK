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
		System.out.println("Method I");
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
		ClausalForm CF = new ClausalForm("coba-coba");
		CF.Print();		
	}

}
