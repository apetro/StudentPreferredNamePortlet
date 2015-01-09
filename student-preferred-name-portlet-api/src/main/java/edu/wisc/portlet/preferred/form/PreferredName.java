package edu.wisc.portlet.preferred.form;

import java.io.Serializable;

public class PreferredName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    
    private String middleName;
    
    private String lastName;
    
    private String pvi;
    
    private boolean hideSource;
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
	  return lastName;
	}

	public void setLastName(String lastName) {
      this.lastName = lastName;
	}
	
	public void setPvi(String pvi) {
		this.pvi = pvi;
	}
	
	public String getPvi() {
		return this.pvi;
	}

	public boolean isHideSource() {
		return hideSource;
	}

	public void setHideSource(boolean hideSource) {
		this.hideSource = hideSource;
	}

	public PreferredName() {
		
	}
	
	public PreferredName(String first, String middle, String last) {
		this.firstName = first;
		this.middleName = middle;
		this.lastName = last;
	}
	
	public PreferredName(String first, String middle, String last, String pvi) {
		this.firstName = first;
		this.middleName = middle;
		this.lastName = last;
		this.pvi = pvi;
	}
	
	public PreferredName(String pvi) {
		this.firstName = null;
		this.middleName = null;
		this.lastName = null;
		this.pvi = pvi;
	}

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + (hideSource ? 1231 : 1237);
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((pvi == null) ? 0 : pvi.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PreferredName other = (PreferredName) obj;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (hideSource != other.hideSource)
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
        return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (pvi == null) {
      if (other.pvi != null)
        return false;
    } else if (!pvi.equals(other.pvi))
      return false;
    return true;
  }

	

}
