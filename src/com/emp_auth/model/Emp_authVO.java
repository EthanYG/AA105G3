package com.emp_auth.model;

public class Emp_authVO
{
	private String emp_no;
	private String auth_no;
	private String target_auth_no;
	
	public String getEmp_no()
	{
		return emp_no;
	}
	public void setEmp_no(String emp_no)
	{
		this.emp_no = emp_no;
	}
	public String getAuth_no()
	{
		return auth_no;
	}
	public void setAuth_no(String auth_no)
	{
		this.auth_no = auth_no;
	}
	public String getTarget_auth_no()
	{
		return target_auth_no;
	}
	public void setTarget_auth_no(String target_auth_no)
	{
		this.target_auth_no = target_auth_no;
	}
	
	
	public boolean equals(Object obj) {
    	if (this == obj) return true;                     
    	if(obj != null && getClass() == obj.getClass()) { 
    		if(obj instanceof Emp_authVO) {
    			Emp_authVO e = (Emp_authVO)obj;
                if (emp_no.equals(emp_no) && auth_no.equals(e.auth_no)) {  
                    return true;
                }
        }
    	}	    	

    	return false;
    }
	
	public int hashCode(){
//      return this.ename.hashCode();               
 	  return new Integer(this.emp_no).hashCode() + new Integer(this.auth_no);  
  	
  }
}
