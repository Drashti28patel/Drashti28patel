package data;

import utilities.Xls_Reader;

public class DataFile {

	Xls_Reader d = new Xls_Reader("C:\\Testing\\NikulTest.xlsx");
	
	//login
	public String wrongEmail = d.getCellData("Data1", "UserName", 3);
	public String wrongPassword =  d.getCellData("Data1", "Password", 2);
	public String emailWithSpecialChar =  d.getCellData("Data1", "UserName", 4);
	public String globalErr =  d.getCellData("Data1", "Email Error", 4);
	public String specialCharErr = d.getCellData("Data1", "Email Error", 5);
	public String emptyEmailErr =  d.getCellData("Data1", "Email Error", 6);
	
	//home
	//footer
	//add to cart
	//checkout
	//search
}
//we add any many as lines here to call data from excel file and then use it in any class just by creating 
//object name eg.: DataFile df = new DataFile(); df.wrongEmail;